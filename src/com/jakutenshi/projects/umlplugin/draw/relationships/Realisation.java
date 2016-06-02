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
        g.setStroke(dashed);
        g.drawLine( (int)getStart().getX(), (int) getStart().getY(),
                (int)getEnd().getX(), (int) getEnd().getY());
    }
}
