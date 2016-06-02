package com.jakutenshi.projects.umlplugin.draw.relationships;

import com.jakutenshi.projects.umlplugin.util.ObservableDrawer;

import java.awt.*;

/**
 * Created by JAkutenshi on 01.06.2016.
 */
public abstract class UMLRelationDrawer implements ObservableDrawer {
    private String startKey;
    private Point start;
    private String endKey;
    private Point end;

    public abstract void drawArrow(Graphics2D g);

    public Point findArrowPoint(Point start, Point end, double length) {
        double PesX = end.getX() - start.getX();
        double PesY = end.getY() - start.getY();
        double A = PesX / PesY;
        double B = (start.getX() * PesY - start.getY() * PesX) / PesY;
        double C = B - end.getX();
        double D = end.getY();
        double alpha = (A * A + 1);
        double beta = 2 * A * C - 2 * D;
        double gamma = C * C + D * D + length * length;
        double y1 = (-beta + Math.sqrt(beta * beta - 4 * alpha * gamma)) / (2 * alpha);
        double y2 = (-beta - Math.sqrt(beta * beta - 4 * alpha * gamma)) / (2 * alpha);
        double x1 = A * y1 + B;
        double x2 = A * y2 + B;
        Point p1 = new Point((int) x1, (int) y1);
        Point p2 = new Point((int) x2, (int) y2);
        Point arrow;
        if (length(start, p1) < length(start, p2)) {
            arrow = p1;
        } else {
            arrow = p2;
        }
        return arrow;
    }

    public double length(Point a, Point b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public UMLRelationDrawer(String startKey, String endKey, Point start, Point end) {
        this.startKey = startKey;
        this.endKey = endKey;
        this.start = start;
        this.end = end;
    }

    public UMLRelationDrawer() {
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setStart(int x, int y) {
        if (start == null) {
            start = new Point();
        }
         start.setLocation(x, y);
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setEnd(int x, int y) {
        if (end == null) {
            end = new Point();
        }
        end.setLocation(x, y);
    }

    public String getStartKey() {
        return startKey;
    }

    public void setStartKey(String startKey) {
        this.startKey = startKey;
    }

    public String getEndKey() {
        return endKey;
    }

    public void setEndKey(String endKey) {
        this.endKey = endKey;
    }

    @Override
    public void onChange(String key, int x, int y) {
        if (key.equals(startKey)) {
           setStart(x, y);
        } else if (key.equals(endKey)) {
            setEnd(x, y);
        }
    }

    public boolean isBindWith(String key) {
        return startKey.equals(key) || endKey.equals(key);
    }
}


