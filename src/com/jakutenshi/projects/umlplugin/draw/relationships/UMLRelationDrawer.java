package com.jakutenshi.projects.umlplugin.draw.relationships;

import com.jakutenshi.projects.umlplugin.draw.GraphicsMethods;
import com.jakutenshi.projects.umlplugin.util.ObservableDrawer;

import java.awt.*;

/**
 * Created by JAkutenshi on 01.06.2016.
 */
public abstract class UMLRelationDrawer implements ObservableDrawer {
    private String startKey;
    private int[] start = {0, 0};
    private String endKey;
    private int[] end = {0, 0};

    public abstract void drawArrow(Graphics2D g);

    public int[] findArrowPoint(int[] start, int[] end, double length) {
        double PesX = end[0] - start[0];
        double PesY = end[1] - start[1];
        double A = PesX / PesY;
        double B = (start[0] * PesY - start[1] * PesX) / PesY;
        double C = B - end[0];
        double D = end[0];
        double alpha = (A * A + 1);
        double beta = 2 * A * C - 2 * D;
        double gamma = C * C + D * D + length * length;
        double y1 = (-beta + Math.sqrt(beta * beta - 4 * alpha * gamma)) / (2 * alpha);
        double y2 = (-beta - Math.sqrt(beta * beta - 4 * alpha * gamma)) / (2 * alpha);
        double x1 = A * y1 + B;
        double x2 = A * y2 + B;
        int[] p1 = {(int)x1, (int)y1};
        int[] p2 = {(int)x2, (int)y2};
        int[] arrow;
        if (GraphicsMethods.length(start, p1) < GraphicsMethods.length(start, p2)) {
            arrow = p1;
        } else {
            arrow = p2;
        }
        return arrow;
    }

    public UMLRelationDrawer(String startKey, String endKey, int[] start, int[] end) {
        this.startKey = startKey;
        this.endKey = endKey;
        this.start = start;
        this.end = end;
    }

    public UMLRelationDrawer() {
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
    }

    public void setStart(int x, int y) {
        if (start == null) {
            start = new int[2];
        }
        start[0] = x;
        start[1] = y;
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] end) {
        this.end = end;
    }

    public void setEnd(int x, int y) {
        if (end == null) {
            end = new int[2];
        }
        end[0] = x;
        end[1] = y;
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


