package com.jakutenshi.projects.umlplugin.drawers.relationships;

import com.jakutenshi.projects.umlplugin.util.ObservableDrawer;

import java.awt.*;

/**
 * Created by JAkutenshi on 01.06.2016.
 */
public abstract class UMLRelationDrawer implements ObservableDrawer {
    private String startKey;
    private int[] startAnchor = {0, 0};
    protected int[] from = new int[2];
    private int[][] startDrawerCoordinates;
    private String endKey;
    private int[] endAnchor = {0, 0};
    protected int[] to = new int[2];
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
    }

    public void setEnd(int x, int y) {
        if (endAnchor == null) {
            endAnchor = new int[2];
        }
        endAnchor[0] = x;
        endAnchor[1] = y;
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

    protected void computeFromAndTo() {
        if (startAnchor[0] == endAnchor[0]
                && startAnchor[1] == endAnchor[1]) {
            return;
        }
        if (from == null) {
            from = new int[2];
        }
        if (to == null) {
            to = new int[2];
        }
        if (endAnchor[0] == startAnchor[0]) {
            from[0] = startAnchor[0];
            to[0] = startAnchor[0];

            from[1] =
                    Math.abs(endAnchor[1] - startDrawerCoordinates[1][1]) <
                    Math.abs(endAnchor[1] - startDrawerCoordinates[1][2]) ?
                            startDrawerCoordinates[1][1] :
                            startDrawerCoordinates[1][2]
            ;
            to[1] =
                    Math.abs(startAnchor[1] - endDrawerCoordinates[1][3]) <
                    Math.abs(startAnchor[1] - endDrawerCoordinates[1][0]) ?
                            endDrawerCoordinates[1][3] :
                            endDrawerCoordinates[1][0]
            ;

        } else if (endAnchor[1] == startAnchor[1]) {
            from[1] = endAnchor[1];
            to[1] = endAnchor[1];

            from[0] =
                    Math.abs(endAnchor[0] - startDrawerCoordinates[0][1]) <
                    Math.abs(endAnchor[0] - startDrawerCoordinates[0][0]) ?
                            startDrawerCoordinates[0][1] :
                            startDrawerCoordinates[0][0]
            ;
            to[0] =
                    Math.abs(startAnchor[0] - endDrawerCoordinates[0][0]) <
                    Math.abs(startAnchor[0] - endDrawerCoordinates[0][2]) ?
                            endDrawerCoordinates[0][0] :
                            endDrawerCoordinates[0][2]
            ;
        } else {
            from = crossWithRect(startDrawerCoordinates);
            to = crossWithRect(endDrawerCoordinates);
        }

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
        double A = ((double)(endAnchor[1] - startAnchor[1])) / ((double) (endAnchor[0] - startAnchor[0]));
        double B = startAnchor[1] - startAnchor[0] * A;

        double cx = 0;
        double cy = 0;
        if (x1 == x2) {
            cx = x1;
            cy = A * cx + B;
        } else if (y1 == y2) {
            cy = y1;
            cx = (cy - B) / A;
        }


        if (isPointInSegment(cx, cy, startAnchor[0], startAnchor[1], endAnchor[0], endAnchor[1])
                && isPointInSegment(cx, cy, x1, y1, x2, y2)) {
            int[] c = new int[2];
            c[0] = (int) cx;
            c[1] = (int) cy;
            return c;
        } else {
            return null;
        }

    }

    protected double length(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    private boolean isPointInSegment(double x, double y, int x1, int y1, int x2, int y2) {
        return ((x1 <= x && x <= x2)
                || (x2 <= x && x <= x1))
                && ((y1 <= y && y <= y2)
                || (y2 <= y && y <= y1));
    }

    protected boolean isRectContacts() {
        boolean is = false;
        for (int i = 0; i < 4; i++) {
            is = isPointInRect(startDrawerCoordinates[0][i],
                    startDrawerCoordinates[1][i], endDrawerCoordinates);
            if (is) return is;
            is = isPointInRect(endDrawerCoordinates[0][i],
                    endDrawerCoordinates[1][i], startDrawerCoordinates);
            if (is) return is;
        }

        is = isPointInRect(startAnchor[0], startAnchor[1], endDrawerCoordinates);
        is = isPointInRect(endAnchor[0], endAnchor[1], startDrawerCoordinates);

        return is;
    }

    private boolean isPointInRect(int x, int y, int[][] rect) {
        return (rect[0][0] <= x && x <= rect[0][1]
                && rect[1][1] <= y && y <= rect[1][2]);
    }
}


