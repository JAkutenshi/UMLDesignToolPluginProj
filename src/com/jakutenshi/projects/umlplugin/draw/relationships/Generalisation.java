package com.jakutenshi.projects.umlplugin.draw.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Generalisation extends UMLRelationDrawer {
    @Override
    public void drawArrow(Graphics2D g) {
        int dx = getEndAnchor()[0] - getStartAnchor()[0],
                dy = getEndAnchor()[1] - getStartAnchor()[1];
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - ARROW_LENGTH,
                xn = xm,
                ym = ARROW_WIDTH,
                yn = -ARROW_WIDTH,
                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + getStartAnchor()[0];
        ym = xm*sin + ym*cos + getStartAnchor()[1];
        xm = x;

        x = xn*cos - yn*sin + getStartAnchor()[0];
        yn = xn*sin + yn*cos + getStartAnchor()[1];
        xn = x;

        int[] xpoints = {(int) xm, getEndAnchor()[0], (int) xn};
        int[] ypoints = {(int) ym, getEndAnchor()[1], (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(getStartAnchor()[0], getStartAnchor()[1], getEndAnchor()[0], getEndAnchor()[1]);
        g.setColor(Color.WHITE);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);
    }
}
