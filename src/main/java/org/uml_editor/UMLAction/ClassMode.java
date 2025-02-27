package org.uml_editor.UMLAction;

import org.uml_editor.UMLObject.ClassShape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassMode extends ModeAction {
    @Override
    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        canvaArea.basicObjects.add(new ClassShape(point));
        canvaArea.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
