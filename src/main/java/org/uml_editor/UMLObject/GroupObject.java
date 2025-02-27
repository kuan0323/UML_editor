package org.uml_editor.UMLObject;

import org.uml_editor.UMLObject.Visitor.Visitor;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject implements BasicObject {
    private ArrayList<BasicObject> groupObjects = new ArrayList<>();

    public GroupObject(ArrayList<BasicObject> objects) {
        this.groupObjects.addAll(objects);
    }
    public void draw(Graphics g) {

    }
    public ArrayList<BasicObject> getShapes() {
        return groupObjects;
    }
    @Override
    public void group(ArrayList<BasicObject> objects) {

    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
