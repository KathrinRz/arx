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

package org.deidentifier.arx.framework.check.groupify;

import org.deidentifier.arx.aggregates.MicroaggregateFunction;
import org.deidentifier.arx.framework.check.distribution.Distribution;
import org.deidentifier.arx.framework.check.groupify.HashGroupify.GroupStatistics;
import org.deidentifier.arx.framework.data.Data;
import org.deidentifier.arx.framework.lattice.Node;

/**
 * Interface for hash groupify operators
 */
public interface IHashGroupify {

    /**
     * Generic adder for all combinations of criteria in mode transform ALL.
     *
     * @param outtuple
     * @param representant
     * @param count
     * @param sensitive
     * @param pcount
     */
    public abstract void addAll(int[] outtuple, int representant, int count, int[] sensitive, int pcount);

    /**
     * Generic adder for all combinations of criteria in mode transform GROUPIFY.
     *
     * @param outtuple
     * @param representant
     * @param count
     * @param distribution
     * @param pcount
     */
    public abstract void addGroupify(int[] outtuple, int representant, int count, Distribution[] distribution, int pcount);


    /**
     * Generic adder for all combinations of criteria in mode transform SNAPSHOT.
     *
     * @param outtuple
     * @param representant
     * @param count
     * @param elements
     * @param frequencies
     * @param pcount
     */
    public abstract void addSnapshot(int[] outtuple, int representant, int count, int[][] elements, int[][] frequencies, int pcount);
    
    /**
     * Computes the anonymity properties and suppressed tuples etc. Must be called
     * when all tuples have been passed to the operator. When the flag is set to true
     * the method will make sure that all equivalence classes that do not fulfill all
     * privacy criteria are marked for suppression. If the flag is set to false,
     * the operator may perform an early abort, which may lead to an inconsistent classification
     * of equivalence classes.
     * 
     * @param transformation
     * @param force
     */
    public abstract void analyze(Node transformation, boolean force);

    /**
     * Clear.
     */
    public abstract void clear();

    /**
     * Gets the first entry.
     * 
     * @return the first entry
     */
    public abstract HashGroupifyEntry getFirstEntry();

    /**
     * Returns statistics about the groups.
     *
     * @return
     */
    public abstract GroupStatistics getGroupStatistics();

    /**
     * Are all defined privacy criteria fulfilled by this transformation, given the specified limit on suppressed tuples.
     *
     * @return true, if successful
     */
    public abstract boolean isAnonymous();
    
    /**
     * Is the current transformation k-anonymous. Always returns true, if no k-anonymity (sub-)criterion was specified
     * 
     * @return
     */
    public abstract boolean isKAnonymous();
    

    /**
     * Marks all outliers.
     *
     * @param buffer
     */
    public abstract void markOutliers(int[][] buffer);
    
    /**
     * Microaggregates.
     *
     * @param dataMIBuffer
     * @param functions
     */
    public abstract void microaggregate(final int[][] data, Data dataMIBuffer, final int startMI, MicroaggregateFunction[] functions);

    /**
     * Resets all flags that indicate that equivalence classes are suppressed.
     */
    public abstract void resetSuppression();
    
    /**
     * Size.
     * 
     * @return the int
     */
    public abstract int size();
}
