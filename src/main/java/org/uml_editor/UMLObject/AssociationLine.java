package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.PortVisitor;

import java.awt.*;
import java.util.ArrayList;

public class AssociationLine extends Line {
    private Point start;
    private Point end;
    public AssociationLine(Point startPoint, Point endPoint, BasicObject startShape, BasicObject endShape) {
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

        if (startPoints == null) {
            return;  // Early exit if there are no valid points
        }
        if (endPoints == null) {
            return;  // Early exit if there are no valid points
        }

        // Find the closest points on startShape and endShape to startX and startY
        start = findClosestPoint(startPoint.x, startPoint.y, startPoints);
        end = findClosestPoint(endPoint.x, endPoint.y, endPoints);

        // the "line"
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);

        // arrow setting
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        double angle = Math.atan2(dy, dx);
        int arrowLength = 15;
        int arrowWidth = 8;

        // draw line
        g2d.drawLine(start.x, start.y, end.x, end.y);

        // arrow head point
        int arrowTipX = (int) (end.x - arrowLength * Math.cos(angle));
        int arrowTipY = (int) (end.y - arrowLength * Math.sin(angle));

        // draw arrow
        Polygon arrowhead = new Polygon();
        arrowhead.addPoint(end.x, end.y);
        arrowhead.addPoint((int) (arrowTipX + arrowWidth * Math.cos(angle + Math.PI / 2)), (int) (arrowTipY + arrowWidth * Math.sin(angle + Math.PI / 2)));
        arrowhead.addPoint((int) (arrowTipX + arrowWidth * Math.cos(angle - Math.PI / 2)), (int) (arrowTipY + arrowWidth * Math.sin(angle - Math.PI / 2)));
        g2d.fillPolygon(arrowhead);
    }


    @Override
    public void group(ArrayList<BasicObject> objects){

    }
}
