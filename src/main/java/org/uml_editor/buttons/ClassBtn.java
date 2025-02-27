package org.uml_editor.buttons;

import org.uml_editor.Config;
import org.uml_editor.Mode;
import org.uml_editor.UMLFrame.CanvaArea;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ClassBtn extends JButton implements ButtonAction {
    public ClassBtn(String buttonName) {
        super(buttonName);
        setMaximumSize(new Dimension(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT));
        setBackground(Config.TOOLBUTTON_NORMAL_COLOR);
        setForeground(Config.TOOLBUTTON_NORMAL_TEXT_COLOR);

        setUI(new BasicButtonUI());
    }
    @Override
    public void enterMode() {
        CanvaArea canvaArea = CanvaArea.getInstance();
        canvaArea.updateCurrentMode(Mode.CLASS);
    }
}
