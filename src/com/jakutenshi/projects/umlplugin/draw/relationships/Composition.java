package com.jakutenshi.projects.umlplugin.draw.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Composition extends UMLRelationDrawer{
    @Override
    public void drawArrow(Graphics2D g) {
        int x1 = getStart()[0];
        int y1 = getStart()[1];
        int x2 = getEnd()[0];
        int y2 = getEnd()[1];
        int d = 15;
        int h = 6;
        int dx = x2 - x1,
                dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d,
                xn = xm,

                xd = D - d * 2,
                yd = 0,

                ym = h,
                yn = -h,
                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        x = xd*cos - yd*sin + x1;
        yd = xd*sin + yd*cos + y1;
        xd = x;

        int[] xpoints = {x2, (int) xm, (int) xd, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yd, (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 4);
    }
}
