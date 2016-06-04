package com.jakutenshi.projects.umlplugin.drawers.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Composition extends UMLRelationDrawer{
    @Override
    public void drawArrow(Graphics2D g) {
        int dx = from[0] - to[0],
                dy = from[1] - to[1];
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - ARROW_LENGTH,
                xn = xm,
                ym = ARROW_WIDTH,
                yn = -ARROW_WIDTH,
                xd = D - ARROW_LENGTH * 2,
                yd = 0,
                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + to[0];
        ym = xm*sin + ym*cos + to[1];
        xm = x;

        x = xn*cos - yn*sin + to[0];
        yn = xn*sin + yn*cos + to[1];
        xn = x;

        x = xd*cos - yd*sin + to[0];
        yd = xd*sin + yd*cos + to[1];
        xd = x;

        int[] xpoints = {from[0], (int) xm, (int) xd, (int) xn};
        int[] ypoints = {from[1], (int) ym, (int) yd, (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(from[0], from[1], to[0], to[1]);
        g.fillPolygon(xpoints, ypoints, 4);
    }
}
