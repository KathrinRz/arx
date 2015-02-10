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

package org.deidentifier.arx.gui.view.impl;

import java.util.List;

import org.deidentifier.arx.gui.Controller;
import org.deidentifier.arx.gui.model.Model;
import org.deidentifier.arx.gui.model.ModelEvent;
import org.deidentifier.arx.gui.model.ModelEvent.ModelPart;
import org.deidentifier.arx.gui.view.def.IView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * This class implements the global main menu.
 * 
 * @author Fabian Prasser
 */
public class MainMenu implements IView {

    /** The menu */
    private Menu       menu;

    /** The controller */
    private Controller controller;

    /** The model */
    private Model      model;

    /**
     * Creates a new instance.
     * 
     * @param shell
     * @param controller
     * @param items
     */
    public MainMenu(final Shell shell, final Controller controller, final List<MainMenuItem> items) {

        // Store
        this.controller = controller;
        controller.addListener(ModelPart.MODEL, this);
        controller.addListener(ModelPart.SELECTED_NODE, this);
        controller.addListener(ModelPart.SELECTED_ATTRIBUTE, this);
        controller.addListener(ModelPart.PERSPECTIVE, this);
        controller.addListener(ModelPart.OUTPUT, this);
        controller.addListener(ModelPart.RESULT, this);

        // Create Menu
        this.menu = new Menu(shell, SWT.BAR);

        // Create items
        this.createItems(menu, items);
        
        // Initialize
        this.update(new ModelEvent(this, ModelPart.MODEL, null));
    }

    /**
     * Creates all according entries
     * @param menu
     * @param items
     */
    private void createItems(Menu menu, List<MainMenuItem> items) {

        // For each item
        for (final MainMenuItem item : items) {

            // Create group
            if (item instanceof MainMenuGroup) {

                MainMenuGroup group = (MainMenuGroup) item;
                MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
                Menu menuMenu = new Menu(menu.getShell(), SWT.DROP_DOWN);
                menuItem.setText(group.getLabel());
                if (group.getImage() != null) {
                    menuItem.setImage(group.getImage());
                }
                menuItem.setMenu(menuMenu);
                createItems(menuMenu, group.getItems());
                menuItem.setEnabled(false);
                menuItem.setData(item);
                
            // Create separator
            } else if (item instanceof MainMenuSeparator) {
                new MenuItem(menu, SWT.SEPARATOR);

                // Create item
            } else {

                MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
                menuItem.setText(item.getLabel());
                if (item.getImage() != null) {
                    menuItem.setImage(item.getImage());
                }
                menuItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        item.action(controller);
                    }
                });
                menuItem.setData(item);
                menuItem.setEnabled(false);
            }
        }
    }

    @Override
    public void dispose() {
        if (!menu.isDisposed()) {
            menu.dispose();
        }
    }

    @Override
    public void reset() {
        this.model = null;
    }

    @Override
    public void update(ModelEvent event) {
        if (event.part == ModelPart.MODEL) {
            if (event.data != null && (event.data instanceof Model)) {
                this.model = (Model)event.data;
            }
        }
        this.update(this.menu, this.model);
    }

    /**
     * Updates the menu
     * @param menu
     * @param model
     */
    private void update(Menu menu, Model model) {

        // Check
        if (menu == null) return;
        
        // For each item
        for (final MenuItem item : menu.getItems()) {

            // Check group
            if (item.getData() instanceof MainMenuGroup) {

                MainMenuGroup group = (MainMenuGroup) item.getData();
                item.setEnabled(group.isEnabled(this.model));
                update(item.getMenu(), model);

                // Check item
            } else {
                MainMenuItem mItem = (MainMenuItem) item.getData();
                item.setEnabled(mItem == null || mItem.isEnabled(this.model));
            }
        }        
    }
}
