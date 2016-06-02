package com.jakutenshi.projects.umlplugin.draw.relationships;

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

        int[] xpoints = {(int) xm, x2, (int) xn};
        int[] ypoints = {(int) ym, y2, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.setStroke(buf);
        g.setColor(Color.BLACK);
        g.drawPolyline(xpoints, ypoints, 3);
    }
}
