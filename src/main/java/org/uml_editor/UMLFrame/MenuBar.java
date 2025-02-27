package org.uml_editor.UMLFrame;

import org.uml_editor.Config;
import org.uml_editor.Mode;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuBar extends JMenuBar {
    private CanvaArea canvaArea;
    private Map<String, JMenuItem> menuItems;

    public MenuBar() {
        canvaArea = CanvaArea.getInstance();
        menuItems = new HashMap<>();

        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open");
        addMenuItem(fileMenu, "Save");
        addMenuItem(fileMenu, "Exit", e -> System.exit(0));

        JMenu editMenu = new JMenu("Edit");
        addMenuItem(editMenu, "Change Object Name", e -> changeObjectName());
//        addMenuItem(editMenu, "Group");
//        addMenuItem(editMenu, "Ungroup");
        addMenuItem(editMenu, "Group", e -> groupObjects());
        addMenuItem(editMenu, "Ungroup", e -> ungroupObjects());

        add(fileMenu);
        add(editMenu);
    }
    private void addMenuItem(JMenu menu, String itemName) {
        addMenuItem(menu, itemName, null);
    }
    private void addMenuItem(JMenu menu, String itemName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(itemName);
        if (action != null) {
            menuItem.addActionListener(action);
        }
        menu.add(menuItem);
        menuItems.put(itemName, menuItem);
    }

    private void changeObjectName() {
        if (canvaArea.getCurrentMode() == Mode.SELECT) {
            if (Config.selectedShape != null) {
                ChangeNameDialog dialog = new ChangeNameDialog((JFrame) SwingUtilities.getWindowAncestor(canvaArea), Config.selectedShape, canvaArea);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a shape to change its name.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot change object name in current mode.");
        }
    }
    private void groupObjects() {
        // Implementation for grouping objects
        if (canvaArea.getCurrentMode() == Mode.SELECT && canvaArea.selectedShapes.size() > 1) {
            canvaArea.groupSelectedObjects();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot group objects in current mode.");
        }
    }

    private void ungroupObjects() {
        // Implementation for ungrouping objects
//        if (canvaArea.getCurrentMode() == Mode.SELECT && canvaArea.selectedShapes.size() == 1) {
//            canvaArea.ungroupSelectedObject();
//        } else {
//            JOptionPane.showMessageDialog(null, "Cannot ungroup objects in current mode.");
//        }
    }

}
