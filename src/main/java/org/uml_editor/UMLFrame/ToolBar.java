package org.uml_editor.UMLFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.uml_editor.Config;
import org.uml_editor.buttons.*;

public class ToolBar extends JToolBar{
    private ArrayList<JButton> buttonGroup = new ArrayList<>();
    private JButton currentSelectedButton = null;
    public ToolBar() {
        setFloatable(false); // 禁止工具欄拖動
        setOrientation(JToolBar.VERTICAL);

        JButton selectBtn = new SelectBtn(Config.TOOLBAR_SELECT_BUTTON_NAME);
        JButton associationBtn = new AssociationBtn(Config.TOOLBAR_ASSOCIATION_BUTTON_NAME);
        JButton generalizationBtn = new GeneralizationBtn(Config.TOOLBAR_GENERALIZATION_BUTTON_NAME);
        JButton compositionBtn = new CompositionBtn(Config.TOOLBAR_COMPOSITION_BUTTON_NAME);
        JButton classBtn = new ClassBtn(Config.TOOLBAR_CLASS_BUTTON_NAME);
        JButton usecaseBtn = new UseCaseBtn(Config.TOOLBAR_USECASE_BUTTON_NAME);

        addButton(selectBtn);
        addButton(associationBtn);
        addButton(generalizationBtn);
        addButton(compositionBtn);
        addButton(classBtn);
        addButton(usecaseBtn);

    }

    private void addButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSelectedButton != null) {
                    currentSelectedButton.setBackground(Config.TOOLBUTTON_NORMAL_COLOR);
                    currentSelectedButton.setForeground(Config.TOOLBUTTON_NORMAL_TEXT_COLOR);
                }
                for (JButton btn : buttonGroup) {
                    if (btn == button) {
                        btn.setBackground(Config.TOOLBUTTON_SELECTED_COLOR);
                        btn.setForeground(Config.TOOLBUTTON_SELECTED_TEXT_COLOR);
                        currentSelectedButton = btn;
                        if (btn instanceof ButtonAction) {
                            ((ButtonAction) btn).enterMode();
                        }
                    } else {
                        btn.setBackground(Config.TOOLBUTTON_NORMAL_COLOR);
                        btn.setForeground(Config.TOOLBUTTON_NORMAL_TEXT_COLOR);
                    }
                }
            }
        });
        add(button);
        add(Box.createRigidArea(new Dimension(0, 20)));
        buttonGroup.add(button);
    }
}