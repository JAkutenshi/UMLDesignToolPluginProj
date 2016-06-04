package com.jakutenshi.projects.umlplugin.drawers.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 03.06.2016.
 */
public class Inclusition extends UMLRelationDrawer {
    @Override
    public void drawArrow(Graphics2D g) {
        computeFromAndTo();
        if (isRectContacts()) return;
        int dx = from[0] - to[0],
                dy = from[1] - to[1];
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - ARROW_WIDTH,
                xn = xm,
                ym = ARROW_WIDTH,
                yn = -ARROW_WIDTH,
                xr = D - ARROW_WIDTH,
                yr = 0,
                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + to[0];
        ym = xm*sin + ym*cos + to[1];
        xm = x;

        x = xn*cos - yn*sin + to[0];
        yn = xn*sin + yn*cos + to[1];
        xn = x;

        x = xr*cos - yr*sin + to[0];
        yr = xr*sin + yr*cos + to[1];
        xr = x;

        g.setColor(Color.BLACK);
        g.drawLine(from[0], from[1], to[0], to[1]);
        g.drawLine((int) xm, (int) ym, (int) xn, (int) yn);
        int r = ARROW_WIDTH * 2;
        xr = xr - (r / 2);
        yr = yr - (r / 2);
        g.drawOval((int) xr, (int) yr, r, r);
    }
}
