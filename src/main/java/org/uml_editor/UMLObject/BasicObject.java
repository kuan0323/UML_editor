package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;
import java.util.ArrayList;

public interface BasicObject {
//    void move(int dx, int dy);
//    void select();
//    void deselect();
//    boolean isSelected();
//    Rectangle getBounds();
//    boolean contains(Point p);
    void draw(Graphics g);
    void group(ArrayList<BasicObject> objects);
    void accept(Visitor visitor);
}
