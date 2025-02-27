package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape implements BasicObject {
    public Point location;
    protected String shapeName = " ";
    public Shape(Point point) {
        location = point;
    }
    public abstract void draw(Graphics g);
    public abstract void drawLabel(Graphics g);
    public abstract void drawPorts(Graphics g);
    public abstract Point[] getPorts();
    public abstract void updateLocation(int dx, int dy);

    public void resetShapeName(String newShapeName) {
        this.shapeName = newShapeName;
    }
    public abstract boolean isPointInside(Point point);
    @Override
    public void group(ArrayList<BasicObject> objects) {

    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
