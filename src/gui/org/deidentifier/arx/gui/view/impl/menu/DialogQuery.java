/*
 * ARX: Efficient, Stable and Optimal Data Anonymization
 * Copyright (C) 2012 - 2013 Florian Kohlmayer, Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.deidentifier.arx.gui.view.impl.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataSelector;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.gui.resources.Resources;
import org.deidentifier.arx.gui.view.SWTUtil;
import org.deidentifier.arx.gui.view.def.IDialog;
import org.deidentifier.arx.gui.view.impl.menu.DialogQueryTokenizer.QueryTokenizerListener;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DialogQuery extends TitleAreaDialog implements IDialog {

    private static enum Operator{
        EQUALS,
        GEQ,
        LEQ,
        LESS,
        GREATER
    }
    
    private Runnable updater = new Runnable(){
        
        private DataSelector previous = null; 
        
        @Override
        public void run() {
            while (!stop){
                try {
                    Thread.sleep(INTERVAL);
                } catch (InterruptedException e) {
                    // Ignore
                }
                if (selector != null && selector != previous){
                    previous = selector;
                    int count = 0;
                    for (int i=0; i<data.getHandle().getNumRows(); i++){
                        count += selector.selected(i) ? 1 : 0;
                    }
                    final int fcount = count;
                    if (status!=null && !status.isDisposed()){
                        Display.getDefault().asyncExec(new Runnable() {
                            public void run() {
                                status.setText(Resources.getMessage("QueryDialog.8")+fcount); //$NON-NLS-1$
                            }
                        });
                    }
                }
            }
        }
    };
    
    private static final int INTERVAL = 500;
    
    private Button           ok          = null;
    private Button           cancel      = null;
    private StyledText       text        = null;
    private Label            status       = null;
    private Data             data        = null;
    private String           queryString = null;
    private DataSelector     selector    = null;
    private DialogQueryTokenizer   highlighter = null;
    private List<StyleRange> styles      = new ArrayList<StyleRange>();
    private boolean          stop        = false;

    public DialogQuery(final Data data, final Shell parent, String initial) {
        super(parent);
        this.queryString = initial;
        this.data = data;
    }

    @Override
    public boolean close() {
        return super.close();
    }

    public DialogQueryResult getResult() {
        return new DialogQueryResult(queryString, selector);
    }
    
    private void highlight() {
        
        if (highlighter==null){
            highlighter = new DialogQueryTokenizer(new QueryTokenizerListener(){

                @Override
                public void and(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_GRAY;
                    styles.add(style);
                }

                @Override
                public void begin(int start) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = 1;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_GREEN;
                    styles.add(style);
                }

                @Override
                public void end(int start) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = 1;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_GREEN;
                    styles.add(style);
                }

                @Override
                public void equals(int start) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = 1;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_BLUE;
                    styles.add(style);
                }

                @Override
                public void field(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_RED;
                    styles.add(style);
                }

                @Override
                public void geq(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_BLUE;
                    styles.add(style);
                }

                @Override
                public void greater(int start) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = 1;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_BLUE;
                    styles.add(style);
                }

                @Override
                public void invalid(int start) {
                    // ignore
                }

                @Override
                public void leq(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_BLUE;
                    styles.add(style);
                }

                @Override
                public void less(int start) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = 1;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_BLUE;
                    styles.add(style);
                }

                @Override
                public void or(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_GRAY;
                    styles.add(style);
                }
                
                @Override
                public void value(int start, int length) {
                    StyleRange style = new StyleRange();
                    style.start = start;
                    style.length = length;
                    style.fontStyle = SWT.BOLD;
                    style.foreground = GUIHelper.COLOR_DARK_GRAY;
                    styles.add(style);
                }
            });
        }
        
        styles.clear();
        highlighter.tokenize(text.getText());

        text.setRedraw(false);
        text.setStyleRanges(styles.toArray(new StyleRange[styles.size()]));        
        text.setRedraw(true);
    }

    private void parse() {
        final String query = text.getText();
        final DataSelector selector = DataSelector.create(data);
        DialogQueryTokenizer parser = new DialogQueryTokenizer(new QueryTokenizerListener(){

                private Operator current = null;
                private DataType<?> type = null;
                
                @Override
                public void and(int start, int length) {
                    selector.and();
                }
            
                @Override
                public void begin(int start) {
                    selector.begin();
                }

                @Override
                public void end(int start) {
                    selector.end();
                }

                @Override
                public void equals(int start) {
                    setCurrent(Operator.EQUALS);
                }

                @Override
                public void field(int start, int length) {
                    String field = query.substring(start+1, start+length-1);
                    int index = data.getHandle().getColumnIndexOf(field);
                    if (index==-1){
                        throw new RuntimeException(Resources.getMessage("QueryDialog.7")+field); //$NON-NLS-1$
                    } else {
                        type = data.getHandle().getDataType(field);
                        selector.field(field);
                    }
                }

                @Override
                public void geq(int start, int length) {
                    setCurrent(Operator.GEQ);
                }

                @Override
                public void greater(int start) {
                    setCurrent(Operator.GREATER);
                }

                @Override
                public void invalid(int start) {
                    throw new RuntimeException(Resources.getMessage("QueryDialog.6")+start); //$NON-NLS-1$
                }

                @Override
                public void leq(int start, int length) {
                    setCurrent(Operator.LEQ);
                }

                @Override
                public void less(int start) {
                    setCurrent(Operator.LESS);
                }

                @Override
                public void or(int start, int length) {
                    selector.or();
                }

                @Override
                public void value(int start, int length) {
                    if (current == null){
                        throw new RuntimeException(Resources.getMessage("QueryDialog.5")+query.substring(start+1, start+length-1)); //$NON-NLS-1$
                    }
                    if (type == null){
                        throw new RuntimeException(Resources.getMessage("QueryDialog.4")+query.substring(start+1, start+length-1)); //$NON-NLS-1$
                    }
                    Object value = type.fromString(query.substring(start+1, start+length-1));
                    switch(current){
                    case EQUALS:
                        if (value instanceof Date){
                            selector.equals((Date)value);
                        } else if (value instanceof String){
                            selector.equals((String)value);
                        } else if (value instanceof Double){
                            selector.equals((Double)value);
                        }
                        break;
                    case GEQ:
                        if (value instanceof Date){
                            selector.geq((Date)value);
                        } else if (value instanceof String){
                            selector.geq((String)value);
                        } else if (value instanceof Double){
                            selector.geq((Double)value);
                        }
                        break;
                    case GREATER:
                        if (value instanceof Date){
                            selector.greater((Date)value);
                        } else if (value instanceof String){
                            selector.greater((String)value);
                        } else if (value instanceof Double){
                            selector.greater((Double)value);
                        }
                        break;
                    case LEQ:
                        if (value instanceof Date){
                            selector.leq((Date)value);
                        } else if (value instanceof String){
                            selector.leq((String)value);
                        } else if (value instanceof Double){
                            selector.leq((Double)value);
                        }
                        break;
                    case LESS:
                        if (value instanceof Date){
                            selector.less((Date)value);
                        } else if (value instanceof String){
                            selector.less((String)value);
                        } else if (value instanceof Double){
                            selector.less((Double)value);
                        }
                        break;
                    }
                    current = null;
                    type = null;
                }

                private void setCurrent(Operator operator){
                    if (current != null) {
                        throw new RuntimeException(Resources.getMessage("QueryDialog.3")+operator); //$NON-NLS-1$
                    } else {
                        current = operator;
                    }
                }
            });
        
        try {
            parser.tokenize(query);
            selector.compile();
        } catch (Exception e){
            this.status.setText(e.getMessage());
            this.ok.setEnabled(false);
            this.selector = null;
            return;
        }
        this.status.setText("OK");
        this.queryString = text.getText();
        this.selector = selector;
        this.ok.setEnabled(true);
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {

        parent.setLayoutData(SWTUtil.createFillGridData());

        // Create OK Button
        ok = createButton(parent, Window.OK, Resources.getMessage("ProjectDialog.3"), true); //$NON-NLS-1$
        ok.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                setReturnCode(Window.OK);
                stop = true;
                close();
            }
        });
        ok.setEnabled(false);

        // Create Cancel Button
        cancel = createButton(parent, Window.CANCEL, Resources.getMessage("ProjectDialog.4"), false); //$NON-NLS-1$
        cancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                setReturnCode(Window.CANCEL);
                stop = true;
                close();
            }
        });
        
        parse();
    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle(Resources.getMessage("QueryDialog.0")); //$NON-NLS-1$
        setMessage(Resources.getMessage("QueryDialog.1"), IMessageProvider.NONE); //$NON-NLS-1$
        return contents;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {

        parent.setLayout(new GridLayout());

        text = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        text.setLayoutData(SWTUtil.createFillGridData());
        text.setText(this.queryString);
        text.addModifyListener(new ModifyListener(){
            @Override
            public void modifyText(ModifyEvent arg0) {
                highlight();
                parse();
            }
        });

        status = new Label(parent, SWT.NONE);
        status.setLayoutData(SWTUtil.createFillHorizontallyGridData());
        status.setText("");
        
        highlight();
        new Thread(updater).start();
        
        return parent;
    }

    @Override
    protected boolean isResizable() {
        return false;
    }
}