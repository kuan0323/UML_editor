package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.PortVisitor;

import java.awt.*;
import java.util.ArrayList;

public class CompositionLine extends Line {
    public CompositionLine(Point startPoint, Point endPoint, BasicObject startShape, BasicObject endShape) {
        super(startPoint, endPoint, startShape, endShape);
    }
    @Override
    public void draw(Graphics g) {
        if (startShape == null || endShape == null) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        PortVisitor startVisitor = new PortVisitor();

        startShape.accept(startVisitor);
        Point[] startPoints = startVisitor.getPortResult();

        PortVisitor endVisitor = new PortVisitor();
        endShape.accept(endVisitor);
        Point[] endPoints = endVisitor.getPortResult();

        Point start = findClosestPoint(startPoint.x, startPoint.y, startPoints);
        Point end = findClosestPoint(endPoint.x, endPoint.y, endPoints);

        // 绘制直线
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(start.x, start.y, end.x, end.y);

        // 绘制空心菱形端点
        int[] xPoints = {end.x, end.x - 10, end.x, end.x + 10};
        int[] yPoints = {end.y, end.y - 5, end.y - 10, end.y - 5};
        g2d.drawPolygon(xPoints, yPoints, 4);
    }

    @Override
    public void group(ArrayList<BasicObject> objects){

    }
}
