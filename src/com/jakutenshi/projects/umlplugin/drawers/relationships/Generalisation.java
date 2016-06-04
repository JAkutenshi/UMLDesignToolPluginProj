package com.jakutenshi.projects.umlplugin.drawers.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Generalisation extends UMLRelationDrawer {
    @Override
    public void drawArrow(Graphics2D g) {
        computeFromAndTo();
        if (isRectContacts()) return;
        int dx = to[0] - from[0],
                dy = to[1] - from[1];
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - ARROW_LENGTH,
                xn = xm,
                ym = ARROW_WIDTH,
                yn = -ARROW_WIDTH,
                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + from[0];
        ym = xm*sin + ym*cos + from[1];
        xm = x;

        x = xn*cos - yn*sin + from[0];
        yn = xn*sin + yn*cos + from[1];
        xn = x;

        int[] xpoints = {(int) xm, to[0], (int) xn};
        int[] ypoints = {(int) ym, to[1], (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(from[0], from[1], to[0], to[1]);
        g.setColor(Color.WHITE);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);
    }
}
