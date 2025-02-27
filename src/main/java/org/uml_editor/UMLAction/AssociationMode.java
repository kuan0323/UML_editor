package org.uml_editor.UMLAction;

import org.uml_editor.UMLObject.AssociationLine;
import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.Visitor.BoundIdentifyVisitor;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AssociationMode extends ModeAction {
    private Point startPoint;  // the line start at
    private Point endPoint;  // the line start at
    private BasicObject startShape = null;
    private BasicObject endShape = null;
    @Override
    public void mouseClicked(MouseEvent e){

    }
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        for (BasicObject shape : canvaArea.basicObjects) {
            BoundIdentifyVisitor visitor = new BoundIdentifyVisitor(startPoint);
            shape.accept(visitor);
            if (visitor.getBoundIdentifyResult() != null) {
                startShape = visitor.getBoundIdentifyResult();
                System.out.println("in bound");
            }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (startPoint != null && startShape != null) {
            endPoint = e.getPoint();
            for (BasicObject shape : canvaArea.basicObjects) {
                BoundIdentifyVisitor visitor = new BoundIdentifyVisitor(endPoint);
                shape.accept(visitor);
                if (visitor.getBoundIdentifyResult() != null) {
                    endShape = visitor.getBoundIdentifyResult();
                }
            }
            if (endShape != startShape) {
                canvaArea.basicObjects.add(new AssociationLine(startPoint, endPoint, startShape, endShape));
                canvaArea.repaint();
            }
            startPoint = null;
            endPoint = null;
            startShape = null;
            endShape = null;
        }
    }
}
