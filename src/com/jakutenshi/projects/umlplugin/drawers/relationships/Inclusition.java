package com.jakutenshi.projects.umlplugin.drawers.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 03.06.2016.
 */
public class Inclusition extends UMLRelationDrawer {
    @Override
    public void drawArrow(Graphics2D g) {
        int dx = getStartAnchor()[0] - getEndAnchor()[0],
                dy = getStartAnchor()[1] - getEndAnchor()[1];
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

        x = xm*cos - ym*sin + getEndAnchor()[0];
        ym = xm*sin + ym*cos + getEndAnchor()[1];
        xm = x;

        x = xn*cos - yn*sin + getEndAnchor()[0];
        yn = xn*sin + yn*cos + getEndAnchor()[1];
        xn = x;

        x = xr*cos - yr*sin + getEndAnchor()[0];
        yr = xr*sin + yr*cos + getEndAnchor()[1];
        xr = x;

        g.setColor(Color.BLACK);
        g.drawLine(getStartAnchor()[0], getStartAnchor()[1], getEndAnchor()[0], getEndAnchor()[1]);
        g.drawLine((int) xm, (int) ym, (int) xn, (int) yn);
        int r = ARROW_WIDTH * 2;
        xr = xr - (r / 2);
        yr = yr - (r / 2);
        g.drawOval((int) xr, (int) yr, r, r);
    }
}
