package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.Config;
import org.uml_editor.Mode;
import org.uml_editor.UMLFrame.CanvaArea;
import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;
import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;

public class PortVisitor implements Visitor {
    private Point[] ports;
    public PortVisitor() {
        ports = new Point[4];
    }
    public Point[] getPortResult() {
        return ports;
    }
    @Override
    public void visit(Shape shape) {
        ports = shape.getPorts();
    }

    @Override
    public void visit(Line line) {
    }

    @Override
    public void visit(GroupObject group) {

    }
}
