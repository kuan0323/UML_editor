package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;
import java.util.ArrayList;

public abstract class Line implements BasicObject {
    protected Point startPoint;
    protected Point endPoint;
    protected BasicObject startShape;
    protected BasicObject endShape;
    public Line(Point startPoint, Point endPoint, BasicObject startShape, BasicObject endShape) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startShape = startShape;
        this.endShape = endShape;
    }
    public abstract void draw(Graphics g);
    protected Point findClosestPoint(int x, int y, Point[] points) {
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        for (Point point : points) {
            double distance = Math.sqrt(Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }
    @Override
    public abstract void group(ArrayList<BasicObject> objects);
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
