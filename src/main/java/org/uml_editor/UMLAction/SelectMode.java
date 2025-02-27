package org.uml_editor.UMLAction;

import org.uml_editor.Config;
import org.uml_editor.UMLObject.BasicObject;
import org.uml_editor.UMLObject.Visitor.BoundIdentifyVisitor;
import org.uml_editor.UMLObject.Visitor.GetLocationVisitor;
import org.uml_editor.UMLObject.Visitor.UpdateLocationVisitor;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectMode extends ModeAction {
    private BasicObject selectPoint = null;
    private Point mousePressPoint;
    private Point mouseReleasePoint;
    private  BasicObject startShape = null;
    private BasicObject endShape = null;
    private Rectangle selectionRectangle;

    @Override
    public void mouseClicked(MouseEvent e){
        Point clickedPoint = e.getPoint();
        boolean clickedInShape = false; // Flag to track if clicked inside any shape
        for (BasicObject shape : canvaArea.basicObjects) {
            BoundIdentifyVisitor visitor = new BoundIdentifyVisitor(clickedPoint);
            shape.accept(visitor);
            if (visitor.getBoundIdentifyResult() != null) {
                System.out.println("selectMode: clicked");
                selectPoint = shape;
                Config.selectedShape = selectPoint;
                clickedInShape = true;
                canvaArea.selectedShapes.clear();
            }
        }
        // If not clicked inside any shape, clear selected shape
        if (!clickedInShape) {
            Config.selectedShape = null;
        }
        canvaArea.repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressPoint = e.getPoint();
        startShape = null;
        for (BasicObject shape : canvaArea.basicObjects) {
            BoundIdentifyVisitor visitor = new BoundIdentifyVisitor(mousePressPoint);
            shape.accept(visitor);
            if (visitor.getBoundIdentifyResult() != null) {
                startShape = shape;
                break;
            }
        }
        if (startShape == null) {
            canvaArea.selectedShapes.clear();
            selectionRectangle = new Rectangle(mousePressPoint);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectionRectangle != null) {
            Point currentPoint = e.getPoint();
            int minX = Math.min(mousePressPoint.x, currentPoint.x);
            int minY = Math.min(mousePressPoint.y, currentPoint.y);
            int width = Math.abs(currentPoint.x - mousePressPoint.x);
            int height = Math.abs(currentPoint.y - mousePressPoint.y);
            selectionRectangle = new Rectangle(minX, minY, width, height);
            canvaArea.repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(startShape != null) { // 有選擇物件要進行移動
            mouseReleasePoint = e.getPoint();  // 放開滑鼠，新位置
            for (BasicObject shape : canvaArea.basicObjects) {   // 判斷新位置有在哪個形狀裡，沒有的話 endShape = null
                BoundIdentifyVisitor visitor = new BoundIdentifyVisitor(mouseReleasePoint);
                shape.accept(visitor);
                if (visitor.getBoundIdentifyResult() != null) {
                    endShape = shape;
                    break;
                }
            }
            if (startShape == endShape) {  // 判斷是不是在原本的形狀做無效移動
                System.out.println("Clicked");
            } else {
                if (endShape == null) {   // 有效移動
                    int dx = mouseReleasePoint.x - mousePressPoint.x;  // 計算移動距離 x
                    int dy = mouseReleasePoint.y - mousePressPoint.y;  // 計算移動距離 y
                    UpdateLocationVisitor updateLocationVisitor = new UpdateLocationVisitor(dx, dy);
                    startShape.accept(updateLocationVisitor);

                    canvaArea.repaint();
                    System.out.println("already release and move");
                }
            }
            startShape = null;
            endShape = null;
            mousePressPoint = null;
            System.out.println("test release");
        }
        else  {
            if (selectionRectangle != null) {
                mouseReleasePoint = e.getPoint();

                int minX = Math.min(mousePressPoint.x, mouseReleasePoint.x);
                int minY = Math.min(mousePressPoint.y, mouseReleasePoint.y);
                int width = Math.abs(mouseReleasePoint.x - mousePressPoint.x);
                int height = Math.abs(mouseReleasePoint.y - mousePressPoint.y);
                selectionRectangle = new Rectangle(minX, minY, width, height);

                canvaArea.setSelectedRectangle(selectionRectangle);

                for (BasicObject shape : canvaArea.basicObjects) {
                    GetLocationVisitor getLocationVisitor = new GetLocationVisitor();
                    shape.accept(getLocationVisitor);
                    Point shapeLocation = getLocationVisitor.getShapeLocationResult();

                    if (shapeLocation != null && selectionRectangle.contains(shapeLocation)) {
                        System.out.println("the shape in ine Rect");
                        canvaArea.selectedShapes.add(shape);
                    }
                }
                selectionRectangle = null;
                canvaArea.repaint();
            }
        }
    }
}

