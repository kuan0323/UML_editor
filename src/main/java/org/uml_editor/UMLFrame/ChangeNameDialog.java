package org.uml_editor.UMLFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.Visitor.ChangeNameVisitor;

public class ChangeNameDialog extends JDialog {
    private JTextField textField;
    private BasicObject selectedShape;
    private CanvaArea canvaArea;

    public ChangeNameDialog(JFrame parent, BasicObject selectedShape, CanvaArea canvaArea) {
        super(parent, "Change Object Name", true);
        this.selectedShape = selectedShape;
        this.canvaArea = canvaArea;
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout());
        textField = new JTextField(20);
        panel.add(new JLabel("New Name: "), BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeObjectName();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void changeObjectName() {
        String newName = textField.getText();
        if (!newName.trim().isEmpty()) {
            ChangeNameVisitor visitor = new ChangeNameVisitor(newName);
            selectedShape.accept(visitor);
            canvaArea.repaint();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

