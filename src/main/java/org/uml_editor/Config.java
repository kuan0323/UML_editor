package org.uml_editor;

import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.Shape;

import java.awt.*;

public class Config {
    public static final int MAINFRAME_WIDTH_SIZE = 1000;
    public static final int MAINFRAME_HEIGHT_SIZE = 800;
    public static final String MAINFRAME_TITLE = "simple UML editor";
    public static final int MAINFRAME_X_LOCATION = (getScreenWidth() - MAINFRAME_WIDTH_SIZE) / 2;
    public static final int MAINFRAME_Y_LOCATION = (getScreenHeight() - MAINFRAME_HEIGHT_SIZE) / 2;
    public static final Color CANVAAREA_COLOR = Color.WHITE;

    public static final String TOOLBAR_SELECT_BUTTON_NAME = "select";
    public static final String TOOLBAR_ASSOCIATION_BUTTON_NAME = "association";
    public static final String TOOLBAR_GENERALIZATION_BUTTON_NAME = "generalization";
    public static final String TOOLBAR_USECASE_BUTTON_NAME = "use case";
    public static final String TOOLBAR_CLASS_BUTTON_NAME = "class";
    public static final String TOOLBAR_COMPOSITION_BUTTON_NAME = "composition";
    public static final int BUTTON_WIDTH = 130;

    public static final int BUTTON_HEIGHT = 150;
    public static final Color TOOLBUTTON_NORMAL_COLOR = Color.GRAY;
    public static final Color TOOLBUTTON_SELECTED_COLOR = Color.BLACK;
    public static final Color TOOLBUTTON_NORMAL_TEXT_COLOR = Color.BLACK;
    public static final Color TOOLBUTTON_SELECTED_TEXT_COLOR = Color.WHITE;
    public static final int CLASSSHAPE_WIDTH = 100;
    public static final int CLASSSHAPE_HEIGHT = 150;
    public static final int USECASESHAPE_WIDTH = 100;
    public static final int USECASESHAPE_HEIGHT = 60;
    public static BasicObject selectedShape;

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static int getScreenWidth() {
        return getScreenSize().width;
    }

    public static int getScreenHeight() {
        return getScreenSize().height;
    }
}
