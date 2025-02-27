package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;
import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;

public class UpdateLocationVisitor implements Visitor {
    private int dx;
    private int dy;
    public UpdateLocationVisitor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    @Override
    public void visit(Shape shape) {
        shape.updateLocation(dx, dy);
    }

    @Override
    public void visit(Line line) {
    }

    @Override
    public void visit(GroupObject group) {
        for (BasicObject obj : group.getShapes()) {
            obj.accept(this);
        }
    }
}
