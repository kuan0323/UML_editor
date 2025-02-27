package org.uml_editor.UMLObject;

import org.uml_editor.Config;

import java.awt.*;

public class ClassShape extends Shape {
    private int minX = location.x;
    private int maxX = location.x + Config.CLASSSHAPE_WIDTH;
    private int minY = location.y;
    private int maxY = location.y + Config.CLASSSHAPE_HEIGHT;
    public int[][] ports = {
            {minX, (minY + maxY) / 2},  // left
            {maxX, (minY + maxY) / 2},  // right
            {(minX + maxX) / 2, minY},  // up
            {(minX + maxX) / 2, maxY}   // down

    };
    public ClassShape(Point point) {
        super(point);
        shapeName = "class";
    }
    public void draw(Graphics g) {
        g.drawRect(location.x, location.y, Config.CLASSSHAPE_WIDTH, Config.CLASSSHAPE_HEIGHT);
    }
    @Override
    public void drawLabel(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int labelWidth = fm.stringWidth(shapeName);
        int labelHeight = fm.getHeight();

        int centerX = location.x + Config.CLASSSHAPE_WIDTH / 2 - labelWidth / 2;
        int centerY = location.y + Config.CLASSSHAPE_HEIGHT / 2 + labelHeight / 2;

        g.drawString(shapeName, centerX, centerY);
    }
    @Override
    public void drawPorts(Graphics g) {
        g.setColor(Color.BLACK);
        for (int[] port : ports) {
            g.fillRect(port[0], port[1], 5, 5);
        }
    }
    @Override
    public void updateLocation(int dx, int dy) {
        int newX = location.x + dx;
        int newY = location.y + dy;
        this.location = new Point(newX, newY);
        this.minX = newX;
        this.maxX = newX + Config.CLASSSHAPE_WIDTH;
        this.minY = newY;
        this.maxY = newY + Config.CLASSSHAPE_HEIGHT;
        this.ports = new int[][] {
                {minX, (minY + maxY) / 2},  // left
                {maxX, (minY + maxY) / 2},  // right
                {(minX + maxX) / 2, minY},  // up
                {(minX + maxX) / 2, maxY}   // down
        };
    }
    @Override
    public boolean isPointInside(Point point) {
        double centralX = location.x + (Config.CLASSSHAPE_WIDTH / 2);
        double centralY = location.y + (Config.CLASSSHAPE_HEIGHT / 2);
        double dx = point.x - centralX;
        double dy = point.y - centralY;
        double result = (dx * dx) / ((Config.CLASSSHAPE_WIDTH / 2) * (Config.CLASSSHAPE_WIDTH / 2)) + (dy * dy) / ((Config.CLASSSHAPE_HEIGHT / 2) * (Config.CLASSSHAPE_HEIGHT / 2));
        return result <= 1;
    }
    @Override
    public Point[] getPorts() {
        return new Point[] {
                new Point(minX, (minY + maxY) / 2),
                new Point(maxX, (minY + maxY) / 2),
                new Point((minX + maxX) / 2, minY),
                new Point((minX + maxX) / 2, maxY)
        };
    }
}
