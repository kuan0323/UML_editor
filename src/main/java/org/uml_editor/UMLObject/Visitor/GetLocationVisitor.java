package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;
import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;

public class GetLocationVisitor implements Visitor {
    private Point shapeLocation;


    public Point getShapeLocationResult() {
        return shapeLocation;
    }

    @Override
    public void visit(Shape shape) {
        shapeLocation = shape.location;
    }

    @Override
    public void visit(Line line) {

    }

    @Override
    public void visit(GroupObject group) {

    }
}
