package org.uml_editor.UMLObject;

import org.uml_editor.Config;

import java.awt.*;

public class UseCaseShape extends Shape {
    private int minX = location.x;
    private int maxX = location.x + Config.USECASESHAPE_WIDTH;
    private int minY = location.y;
    private int maxY = location.y + Config.USECASESHAPE_HEIGHT;
    public int[][] ports = {
            {minX, (minY + maxY) / 2},  // left
            {maxX, (minY + maxY) / 2},  // right
            {(minX + maxX) / 2, minY},  // up
            {(minX + maxX) / 2, maxY}   // down

    };
    public UseCaseShape(Point point) {
        super(point);
        shapeName = "use case";
    }
    @Override
    public void draw(Graphics g) {
        g.drawOval(location.x, location.y, Config.USECASESHAPE_WIDTH, Config.USECASESHAPE_HEIGHT);
    }
    @Override
    public void drawLabel(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int labelWidth = fm.stringWidth(shapeName);
        int labelHeight = fm.getHeight();

        int centerX = location.x + Config.USECASESHAPE_WIDTH / 2 - labelWidth / 2;
        int centerY = location.y + Config.USECASESHAPE_HEIGHT / 2 + labelHeight / 2;

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
    public boolean isPointInside(Point point) {
        double centralX = location.x + (Config.USECASESHAPE_WIDTH / 2);
        double centralY = location.y + (Config.USECASESHAPE_HEIGHT / 2);
        double dx = point.x - centralX;
        double dy = point.y - centralY;
        double result = (dx * dx) / ((Config.USECASESHAPE_WIDTH / 2) * (Config.USECASESHAPE_WIDTH / 2)) + (dy * dy) / ((Config.USECASESHAPE_HEIGHT / 2) * (Config.USECASESHAPE_HEIGHT / 2));
        return result <= 1;
    }
    @Override
    public void updateLocation(int dx, int dy) {
        int newX = location.x + dx;
        int newY = location.y + dy;
        this.location = new Point(newX, newY);
        this.minX = newX;
        this.maxX = newX + Config.USECASESHAPE_WIDTH;
        this.minY = newY;
        this.maxY = newY + Config.USECASESHAPE_HEIGHT;
        this.ports = new int[][] {
                {minX, (minY + maxY) / 2},  // left
                {maxX, (minY + maxY) / 2},  // right
                {(minX + maxX) / 2, minY},  // up
                {(minX + maxX) / 2, maxY}   // down
        };
    }
    @Override
    public Point[] getPorts() {
        System.out.println("in the getPorts()");
        return new Point[] {
                new Point(minX, (minY + maxY) / 2),
                new Point(maxX, (minY + maxY) / 2),
                new Point((minX + maxX) / 2, minY),
                new Point((minX + maxX) / 2, maxY)
        };
    }
}
