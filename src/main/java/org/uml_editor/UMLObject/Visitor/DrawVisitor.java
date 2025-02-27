package org.uml_editor.UMLObject.Visitor;

import org.uml_editor.Config;
import org.uml_editor.Mode;
import org.uml_editor.UMLFrame.CanvaArea;
import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.GroupObject;
import org.uml_editor.UMLObject.Line;
import org.uml_editor.UMLObject.Shape;

import java.awt.Graphics;
import java.util.ArrayList;

public class DrawVisitor implements Visitor {
    private Graphics g;
    private CanvaArea canvaArea;
    private boolean groupVisit;
    private ArrayList<BasicObject> groupObjects = new ArrayList<>();

    public DrawVisitor(Graphics g) {
        this.g = g;
        canvaArea = CanvaArea.getInstance();
    }

    @Override
    public void visit(Shape shape) {
        shape.draw(g);
        shape.drawLabel(g);
        if (CanvaArea.getInstance().getCurrentMode() == Mode.SELECT && shape == Config.selectedShape) {
            shape.drawPorts(g);
        }
        if (CanvaArea.getInstance().getCurrentMode() == Mode.SELECT && canvaArea.selectedShapes.contains(shape)) { // multi-select
            shape.drawPorts(g);
        }
        if (CanvaArea.getInstance().getCurrentMode() == Mode.SELECT && groupVisit == true) {
            shape.drawPorts(g);
        }
    }

    @Override
    public void visit(Line line) {
        line.draw(g);
    }

    @Override
    public void visit(GroupObject group) {
        if (Config.selectedShape == group || canvaArea.selectedShapes.contains(group)) {
            System.out.println("drawVisitor: Config.selectedShape == group!");
            groupObjects = group.getShapes();
            groupVisit = true;
        }
        for (BasicObject obj : group.getShapes()) {
            System.out.println("drawVisitor group");
            obj.accept(this);
        }
//        groupObjects.clear();
        groupVisit = false;
    }
}
