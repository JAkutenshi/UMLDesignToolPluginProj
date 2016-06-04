package com.jakutenshi.projects.umlplugin.drawers.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 31.05.2016.
 */
public class Realisation extends UMLRelationDrawer {
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);

    @Override
    public void drawArrow(Graphics2D g) {
        Stroke buf = g.getStroke();
        g.setStroke(dashed);
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

        g.drawLine(getStartAnchor()[0], getStartAnchor()[1], getEndAnchor()[0], getEndAnchor()[1]);
        g.setStroke(buf);
        g.setColor(Color.BLACK);
        g.drawPolyline(xpoints, ypoints, 3);
    }
}
