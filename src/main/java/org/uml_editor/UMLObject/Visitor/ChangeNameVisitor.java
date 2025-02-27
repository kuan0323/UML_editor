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

public class ChangeNameVisitor implements Visitor {
    private String newName;
    public ChangeNameVisitor(String newName) {
        this.newName = newName;
    }

    @Override
    public void visit(Shape shape) {
        shape.resetShapeName(newName);
    }

    @Override
    public void visit(Line line) {
    }

    @Override
    public void visit(GroupObject group) {

    }
}
