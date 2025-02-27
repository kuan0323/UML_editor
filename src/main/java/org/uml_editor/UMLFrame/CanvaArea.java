package org.uml_editor.UMLFrame;

import org.uml_editor.Config;
import org.uml_editor.Mode;
import org.uml_editor.UMLAction.ModeAction;
import org.uml_editor.UMLObject.*;
import org.uml_editor.UMLObject.Visitor.DrawVisitor;
import org.uml_editor.UMLObject.Visitor.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CanvaArea extends JPanel {
    private static CanvaArea canvaArea;
    private Mode currentMode;
    private Rectangle selectedRectangle;
    public List<BasicObject> basicObjects = new ArrayList<>();
    public ArrayList<BasicObject> selectedShapes = new ArrayList<>();
    private CanvaArea() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ModeAction action = ModeAction.getInstance(currentMode);
                if (action != null) {
                    action.mouseClicked(e);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                ModeAction action = ModeAction.getInstance(currentMode);
                if (action != null) {
                    action.mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ModeAction action = ModeAction.getInstance(currentMode);
                if (action != null) {
                    action.mouseReleased(e);
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                ModeAction action = ModeAction.getInstance(currentMode);
                if (action != null) {
                    action.mouseDragged(e);
                }
            }
        });
    }
    public static CanvaArea getInstance(){
        // create an object when it is called at first time
        if(canvaArea == null){
            canvaArea = new CanvaArea();
        }
        return canvaArea;
    }
    public void updateCurrentMode(Mode mode) {
        currentMode = mode;
    }
    public Mode getCurrentMode() {
        return currentMode;
    }
    public void setSelectedRectangle(Rectangle selectedRectangle) {
        this.selectedRectangle = selectedRectangle;
        repaint();
    }
    public Rectangle getSelecedRectangle() {
        return selectedRectangle;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Visitor drawVisitor = new DrawVisitor(g);
        for (BasicObject obj : basicObjects) {
            obj.accept(drawVisitor);
        }
        if (currentMode == Mode.SELECT && selectedShapes.size() > 0) {
            g.setColor(new Color(200, 200, 200, 100));  // 設置顏色和透明度
            ((Graphics2D) g).fill(selectedRectangle);  // 繪製選取矩形
        }
    }
    public void groupSelectedObjects() {
        if (selectedShapes.size() > 1) {
            BasicObject group = new GroupObject(selectedShapes);
            basicObjects.removeAll(selectedShapes);
            basicObjects.add(group);
            selectedShapes.clear();
            repaint();
        }
    }

//    public void ungroupSelectedObject() {
//        if ((selectedShapes.size() == 1 && selectedShapes.get(0) instanceof GroupObject) || Config.selectedShape ==) {
//            GroupObject group = (GroupObject) selectedShapes.get(0);
//            ArrayList<BasicObject> ungroupedShapes = group.getShapes();
//            basicObjects.remove(group);
//            basicObjects.addAll(ungroupedShapes);
//            selectedShapes.clear();
//            selectedShapes.addAll(ungroupedShapes);
//            repaint();
//        }
//    }
}
