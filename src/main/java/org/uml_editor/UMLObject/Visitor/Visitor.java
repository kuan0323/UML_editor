package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;

public interface Visitor {
    void visit(Shape shape);
    void visit(Line line);
    void visit(GroupObject group);
}
