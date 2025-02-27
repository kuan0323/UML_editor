package org.uml_editor.UMLFrame;

import javax.swing.*;
import java.awt.*;

import org.uml_editor.Config;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle(Config.MAINFRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Config.MAINFRAME_WIDTH_SIZE, Config.MAINFRAME_HEIGHT_SIZE);

        MenuBar menuBar = new MenuBar();
        ToolBar toolBar = new ToolBar();
        CanvaArea canvaArea = CanvaArea.getInstance();

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(toolBar, BorderLayout.WEST);
        mainPanel.add(canvaArea, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setLocation(Config.MAINFRAME_X_LOCATION, Config.MAINFRAME_Y_LOCATION);
    }

}

