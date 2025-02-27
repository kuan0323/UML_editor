package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.PortVisitor;

import java.awt.*;
import java.util.ArrayList;

public class GeneralizationLine extends Line {
    public GeneralizationLine(Point startPoint, Point endPoint, BasicObject startShape, BasicObject endShape) {
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

        // straight line
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);

        // draw the straight line
        g2d.drawLine(start.x, start.y, end.x, end.y);

        // draw the arrow
//        drawArrow(g2d, start, end, 15);
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        double angle = Math.atan2(dy, dx);

        int x1 = (int) (end.x - 15 * Math.cos(angle - Math.PI / 6));
        int y1 = (int) (end.y - 15 * Math.sin(angle - Math.PI / 6));
        int x2 = (int) (end.x - 15 * Math.cos(angle + Math.PI / 6));
        int y2 = (int) (end.y - 15 * Math.sin(angle + Math.PI / 6));
        int[] arrowXPoints = new int[]{end.x, x1, x2};
        int[] arrowYPoints = new int[]{end.y, y1, y2};
        g2d.drawPolygon(arrowXPoints, arrowYPoints, 3);
    }
    @Override
    public void group(ArrayList<BasicObject> objects){

    }
}
