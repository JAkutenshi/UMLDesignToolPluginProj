package com.jakutenshi.projects.umlplugin.draw.relationships;

import java.awt.*;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Composition extends UMLRelationDrawer{
    @Override
    public void drawArrow(Graphics2D g) {
        g.setStroke(new BasicStroke(4));
        g.drawLine((int) getStart().getX(), (int) getStart().getY(),
                (int) getEnd().getX(), (int) getEnd().getY());


    }
}
