package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;
import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;

public class BoundIdentifyVisitor implements Visitor {
    private Point point;
    private BasicObject boundIdentifyResult = null;

    public BoundIdentifyVisitor(Point point) {
        this.point = point;
    }

    public BasicObject getBoundIdentifyResult() {
        return boundIdentifyResult;
    }

    @Override
    public void visit(Shape shape) {
        if(shape.isPointInside(point)) {
            boundIdentifyResult = shape;
        } else {
            boundIdentifyResult = null;
        }
    }

    @Override
    public void visit(Line line) {

    }

    @Override
    public void visit(GroupObject group) {
        for (BasicObject obj : group.getShapes()) {
            obj.accept(this);
            if (boundIdentifyResult != null) {
                break; // 如果找到點擊的形狀，停止訪問
            }
            System.out.println("chect if click group element");
        }
    }
}
