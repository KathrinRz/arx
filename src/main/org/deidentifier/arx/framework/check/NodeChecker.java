/*
 * ARX: Powerful Data Anonymization
 * Copyright 2012 - 2015 Florian Kohlmayer, Fabian Prasser
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.deidentifier.arx.framework.check;

import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXConfiguration.ARXConfigurationInternal;
import org.deidentifier.arx.aggregates.MicroaggregateFunction;
import org.deidentifier.arx.framework.check.StateMachine.Transition;
import org.deidentifier.arx.framework.check.distribution.IntArrayDictionary;
import org.deidentifier.arx.framework.check.groupify.HashGroupify;
import org.deidentifier.arx.framework.check.groupify.IHashGroupify;
import org.deidentifier.arx.framework.check.history.History;
import org.deidentifier.arx.framework.data.Data;
import org.deidentifier.arx.framework.data.DataManager;
import org.deidentifier.arx.framework.lattice.Lattice;
import org.deidentifier.arx.framework.lattice.Node;
import org.deidentifier.arx.metric.InformationLoss;
import org.deidentifier.arx.metric.InformationLossWithBound;
import org.deidentifier.arx.metric.Metric;

/**
 * This class orchestrates the process of checking a node for k-anonymity.
 *
 * @author Fabian Prasser
 * @author Florian Kohlmayer
 */
public class NodeChecker implements INodeChecker {

    /** The config. */
    private final ARXConfigurationInternal config;

    /** The data. */
    private final Data             data;
    
    /** The microaggregate data. */
    private final Data                        dataMIBuffer;
        /** The microaggregate functions. */
    private final MicroaggregateFunction<?>[] functionsMI;
    /** The microaggregate start index. */
    private final int                         startMI;

    /** The current hash groupify. */
    protected IHashGroupify        currentGroupify;

    /** The history. */
    protected History              history;

    /** The last hash groupify. */
    protected IHashGroupify        lastGroupify;

    /** The metric. */
    protected Metric<?>            metric;

    /** The state machine. */
    protected StateMachine         stateMachine;

    /** The data transformer. */
    protected Transformer          transformer;

    /**
     * Creates a new NodeChecker instance.
     * 
     * @param manager
     *            The manager
     * @param metric
     *            The metric
     * @param config
     *            The anonymization configuration
     * @param historyMaxSize
     *            The history max size
     * @param snapshotSizeDataset
     *            The history threshold
     * @param snapshotSizeSnapshot
     *            The history threshold replacement
     */
    public NodeChecker(final DataManager manager, final Metric<?> metric, final ARXConfigurationInternal config, final int historyMaxSize, final double snapshotSizeDataset, final double snapshotSizeSnapshot) {

        // Initialize all operators
        this.metric = metric;
        this.config = config;
        this.data = manager.getDataQI();
        // Make copy of the microaggregation columns for the output transformation
        this.dataMIBuffer = manager.getDataMI().clone();
        this.functionsMI = manager.getMicroaggregationFunctions();
        
        int initialSize = (int) (manager.getDataQI().getDataLength() * 0.01d);
        IntArrayDictionary dictionarySensValue;
        IntArrayDictionary dictionarySensFreq;
        if ((config.getRequirements() & ARXConfiguration.REQUIREMENT_DISTRIBUTION) != 0) {
            dictionarySensValue = new IntArrayDictionary(initialSize);
            dictionarySensFreq = new IntArrayDictionary(initialSize);
        } else {
            // Just to allow byte code instrumentation
            dictionarySensValue = new IntArrayDictionary(0);
            dictionarySensFreq = new IntArrayDictionary(0);
        }
        // TODO: Ugly! Integrate microaggregation into transformer?
        // Copy arrays
        int[][] attributeWithDistribution;
        if (config.isMicroaggregation()) {
            int sizeSE = manager.getDataSE().getHeader().length;
            int sizeMI = manager.getDataMI().getHeader().length;
            int rows = Math.max(manager.getDataSE().getArray().length, manager.getDataMI().getArray().length);
            attributeWithDistribution = new int[rows][sizeSE + sizeMI];
            for (int i = 0; i < attributeWithDistribution.length; i++) {
                for (int j = 0; j < attributeWithDistribution[i].length; j++) {
                    if (j < sizeSE) {
                        attributeWithDistribution[i][j] = manager.getDataSE().getArray()[i][j];
                    } else {
                        attributeWithDistribution[i][j] = manager.getDataMI().getArray()[i][j - sizeSE];
                    }
                }
            }
            startMI = sizeSE;
        } else {
            attributeWithDistribution = manager.getDataSE().getArray();
            startMI = -1;
        }

        this.history = new History(manager.getDataQI().getArray().length,
                                   historyMaxSize,
                                   snapshotSizeDataset,
                                   snapshotSizeSnapshot,
                                   config,
                                   dictionarySensValue,
                                   dictionarySensFreq);
        
        this.stateMachine = new StateMachine(history);
        this.currentGroupify = new HashGroupify(initialSize, config);
        this.lastGroupify = new HashGroupify(initialSize, config);
        this.transformer = new Transformer(manager.getDataQI().getArray(),
                                           manager.getHierarchies(),
                                           attributeWithDistribution,
                                           config,
                                           dictionarySensValue,
                                           dictionarySensFreq);
    }

    @Override
    public TransformedData applyAndSetProperties(final Node transformation) {

        // Apply transition and groupify
        currentGroupify = transformer.apply(0L, transformation.getTransformation(), currentGroupify);
        currentGroupify.analyze(transformation, true);
        if (!currentGroupify.isAnonymous() && !config.isSuppressionAlwaysEnabled()) {
            currentGroupify.resetSuppression();
        }

        // Determine information loss
        // TODO: This may already be known
        InformationLoss<?> loss = transformation.getInformationLoss();
        if (loss == null) {
            loss = metric.getInformationLoss(transformation, currentGroupify).getInformationLoss();
        }
        
        // Find outliers
        if (config.getAbsoluteMaxOutliers() != 0 || !currentGroupify.isAnonymous()) {
            currentGroupify.markOutliers(transformer.getBuffer());
        }
        
        // Microaggregate
        if (config.isMicroaggregation()) {
            currentGroupify.microaggregate(transformer.getBuffer(), dataMIBuffer, startMI, functionsMI);
        }
        
        // Set properties
        Lattice lattice = new Lattice(new Node[][]{{transformation}}, 0);
        lattice.setChecked(transformation, new Result(currentGroupify.isAnonymous(), 
                                                      currentGroupify.isKAnonymous(),
                                                      loss,
                                                      null));
        
        // Return the buffer
        return new TransformedData(getBuffer(), dataMIBuffer, currentGroupify.getGroupStatistics());
    }

    @Override
    public INodeChecker.Result check(final Node node) {
        return check(node, false);
    }

    @Override
    public INodeChecker.Result check(final Node node, final boolean forceMeasureInfoLoss) {
        
        // If the result is already know, simply return it
        if (node.getData() != null && node.getData() instanceof INodeChecker.Result) {
            return (INodeChecker.Result)node.getData();
        }

        // Store snapshot from last check
        if (stateMachine.getLastNode() != null) {
            history.store(stateMachine.getLastNode(), currentGroupify, stateMachine.getLastTransition().snapshot);
        }

        // Transition
        final Transition transition = stateMachine.transition(node);

        // Switch groupifies
        final IHashGroupify temp = lastGroupify;
        lastGroupify = currentGroupify;
        currentGroupify = temp;

        // Apply transition
        switch (transition.type) {
        case UNOPTIMIZED:
            currentGroupify = transformer.apply(transition.projection, node.getTransformation(), currentGroupify);
            break;
        case ROLLUP:
            currentGroupify = transformer.applyRollup(transition.projection, node.getTransformation(), lastGroupify, currentGroupify);
            break;
        case SNAPSHOT:
            currentGroupify = transformer.applySnapshot(transition.projection, node.getTransformation(), currentGroupify, transition.snapshot);
            break;
        }
        
        // We are done with transforming and adding
        currentGroupify.analyze(node, forceMeasureInfoLoss);
        if (forceMeasureInfoLoss && !currentGroupify.isAnonymous() && !config.isSuppressionAlwaysEnabled()) {
            currentGroupify.resetSuppression();
        }
        
        // Compute information loss and lower bound
        InformationLossWithBound<?> result = (currentGroupify.isAnonymous() || forceMeasureInfoLoss) ?
                                          metric.getInformationLoss(node, currentGroupify) : null;
        InformationLoss<?> loss = result != null ? result.getInformationLoss() : null;
        InformationLoss<?> bound = result != null ? result.getLowerBound() : metric.getLowerBound(node, currentGroupify);
        
        // Return result;
        return new INodeChecker.Result(currentGroupify.isAnonymous(), 
                                       currentGroupify.isKAnonymous(),
                                       loss,
                                       bound);
    }

    @Override
    public Data getBuffer() {
        return new Data(transformer.getBuffer(), data.getHeader(), data.getMap(), data.getDictionary());
    }

    @Override
    public ARXConfigurationInternal getConfiguration() {
        return config;
    }

    @Override
    @Deprecated
    public Data getData() {
        return data;
    }

    @Override
    public IHashGroupify getGroupify() {
        return currentGroupify;
    }

    /**
     * Returns the checkers history, if any.
     *
     * @return
     */
    public History getHistory() {
        return history;
    }

    @Override
    @Deprecated
    public double getInformationLoss(final Node node) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public Metric<?> getMetric() {
        return metric;
    }
}
