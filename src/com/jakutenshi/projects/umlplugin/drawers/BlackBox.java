package com.jakutenshi.projects.umlplugin.drawers;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.awt.*;

/**
 * Created by JAkutenshi on 31.05.2016.
 */
public class BlackBox extends UMLDrawer {
    private DrawnLine path;

    public BlackBox(String path) {
        this.path = new DrawnLine(path);
        setKey(path);
        setFrameHeight(FRAME_MARGIN * 2 + SYMBOL_HEIGHT);
        setFrameWidth(SYMBOL_HEIGHT * path.length() + 2 * FRAME_MARGIN);
    }

    @Override
    public void draw(Graphics2D g) {
        int currentY = getY() + FRAME_MARGIN + SYMBOL_HEIGHT;
        drawFrame(g);
        drawLine(currentY, path, g);
    }

    @Override
    protected void fillContent(UMLEntity entity) {

    }

}
