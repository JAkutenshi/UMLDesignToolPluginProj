package com.jakutenshi.projects.umlplugin.draw;

import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumConstant;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by JAkutenshi on 01.06.2016.
 */
public class EnumDrawer extends UMLDrawer {
    private LinkedList<DrawnLine> drawnEnumConstants;

    public EnumDrawer(UMLEntity entity) {
        super(entity);
    }

    @Override
    public void draw(Graphics2D g) {
        int currentY = getY() + FRAME_MARGIN + SYMBOL_HEIGHT;

//рисуем прямоугольник
        drawFrame(g);
//заголовок
        currentY = drawTitle(currentY, g);
//разделитель
        currentY = drawSeparator(currentY, g);
//константы
        drawSection(currentY, drawnEnumConstants, g);
    }

    @Override
    protected void fillContent(UMLEntity entity) {
        Enum anEnum = (Enum) entity;
        drawnEnumConstants = new LinkedList<>();
        int maxLength = getDrawnTitle().getLine().length();

        for (EnumConstant enumConstant : anEnum.getEnumConstants()) {
            drawnEnumConstants.addFirst(new DrawnLine(enumConstant.toUML()));
            if (drawnEnumConstants.getFirst().getLine().length() > maxLength) {
                maxLength = drawnEnumConstants.getFirst().getLine().length();
            }
        }

        setFrameWidth(maxLength * SYMBOL_WIDTH + 2 * FRAME_MARGIN);
        setFrameHeight((SYMBOL_HEIGHT + LINE_SPACING) //заголовок
                + SEPARATOR_HEIGHT * 2
                + (drawnEnumConstants.size() ==  0 ? 1 : drawnEnumConstants.size())
                * (SYMBOL_HEIGHT + LINE_SPACING)
                + 2 * FRAME_MARGIN);
    }

    @Override
    public void fillRelations(UMLEntity entity) {

    }
}
