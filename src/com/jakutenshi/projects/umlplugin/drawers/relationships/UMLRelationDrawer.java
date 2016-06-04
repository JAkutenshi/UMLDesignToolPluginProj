package com.jakutenshi.projects.umlplugin.drawers.relationships;

import com.jakutenshi.projects.umlplugin.util.ObservableDrawer;

import java.awt.*;

/**
 * Created by JAkutenshi on 01.06.2016.
 */
public abstract class UMLRelationDrawer implements ObservableDrawer {
    private String startKey;
    private int[] startAnchor = {0, 0};
    protected int[] from = startAnchor;
    private int[][] startDrawerCoordinates;
    private String endKey;
    private int[] endAnchor = {0, 0};
    protected int[] to = endAnchor;
    private int[][] endDrawerCoordinates;


    public final static int ARROW_WIDTH = 6;
    public final static int ARROW_LENGTH = 15;

    public abstract void drawArrow(Graphics2D g);



    public UMLRelationDrawer(String startKey, String endKey, int[] startAnchor, int[] endAnchor) {
        this.startKey = startKey;
        this.endKey = endKey;
        this.startAnchor = startAnchor;
        this.endAnchor = endAnchor;
    }

    public UMLRelationDrawer() {
    }

    public int[] getStartAnchor() {
        return startAnchor;
    }

    public void setStartAnchor(int[] startAnchor) {
        this.startAnchor = startAnchor;
    }



    public int[] getEndAnchor() {
        return endAnchor;
    }

    public void setEndAnchor(int[] endAnchor) {
        this.endAnchor = endAnchor;
    }

    public void setStart(int x, int y) {
        if (startAnchor == null) {
            startAnchor = new int[2];
        }
        startAnchor[0] = x;
        startAnchor[1] = y;
        //computeFromAndTo();
    }

    public void setEnd(int x, int y) {
        if (endAnchor == null) {
            endAnchor = new int[2];
        }
        endAnchor[0] = x;
        endAnchor[1] = y;
        //computeFromAndTo();
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
    public void onChange(String key, int x, int y, int[][] coords) {
        if (key.equals(startKey)) {
            startDrawerCoordinates = coords;
            setStart(x, y);
        } else if (key.equals(endKey)) {
            endDrawerCoordinates = coords;
            setEnd(x, y);
        }
    }

    public void setStartDrawerCoordinates(int[][] startDrawerCoordinates) {
        this.startDrawerCoordinates = startDrawerCoordinates;
    }

    public void setEndDrawerCoordinates(int[][] endDrawerCoordinates) {
        this.endDrawerCoordinates = endDrawerCoordinates;
    }

    public boolean isBindWith(String key) {
        return startKey.equals(key) || endKey.equals(key);
    }

    private void computeFromAndTo() {
        if (startAnchor[0] == endAnchor[0]
                && startAnchor[1] == endAnchor[1]) return;
        int[] fromCrossPoint = crossWithRect(startDrawerCoordinates);
        int[] toCrossPoint = crossWithRect(endDrawerCoordinates);

    }

    private int[] crossWithRect(int[][] rectCoords) {
        int[] crossPoint = null;
        for (int i = 0; i < 3; i++) {
            crossPoint = crossPoint(rectCoords[0][i],
                    rectCoords[1][i],
                    rectCoords[0][i + 1],
                    rectCoords[1][i + 1]);
            if (crossPoint != null) break;
        }
        if (crossPoint == null) {
            crossPoint = crossPoint(rectCoords[0][0],
                    rectCoords[1][0],
                    rectCoords[0][3],
                    rectCoords[1][3]);
        }
        return crossPoint;
    }

    private int[] crossPoint(int x1, int y1, int x2, int y2) {
        double A = (endAnchor[1] - startAnchor[1]) / (endAnchor[0] - startAnchor[0]);
        double B = startAnchor[1] - startAnchor[0] * A;
        double cx = 0;
        double cy = 0;
        if (x1 == x2) {
            cx = x1;
            cy = A * cx + B;
        } else if (y1 == y2) {
            cy = y1;
            cx = cy / A - B;
        }

        if (startAnchor[0] < cx && cx < endAnchor[0]
                && startAnchor[1] < cy && cy < endAnchor[1]) {
            int[] c = new int[2];
            c[0] = (int) cx;
            c[1] = (int) cy;
            return c;
        } else {
            return null;
        }

    }
}


