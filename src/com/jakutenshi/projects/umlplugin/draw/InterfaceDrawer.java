package com.jakutenshi.projects.umlplugin.draw;

import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by JAkutenshi on 29.05.2016.
 */
public class InterfaceDrawer extends UMLDrawer {
    private LinkedList<DrawnLine> drawnSignatures;

    public InterfaceDrawer(UMLEntity entity) {
        super(entity);

    }

    @Override
    public void draw(Graphics2D g) {
        int currentY = getY() + FRAME_MARGIN + SYMBOL_HEIGHT;
//рисуем прямоугольник
        drawFrame(g);
//заголовок
        currentY = drawLine(currentY, getDrawnTitle(), g);
//разделитель
        currentY = drawSeparator(currentY, g);
//сигнатуры
        drawSection(currentY, drawnSignatures, g);
    }

    @Override
    public void fillContent(UMLEntity entity) {
        Interface anInterface = (Interface) entity;
        drawnSignatures = new LinkedList<>();

        int maxLength = getDrawnTitle().getLine().length(); //длина строки для вычисления ширины

        for (Method signature : anInterface.getSignatures()) {
            drawnSignatures.addFirst(new DrawnLine(signature.toUML()));
            if (signature.getKeywords().contains("static")) {
                makeFontUnderlined(drawnSignatures.getFirst().getFont());
            }
            if (drawnSignatures.getFirst().getLine().length() > maxLength) {
                maxLength = drawnSignatures.getFirst().getLine().length();
            }
        }

        setFrameWidth(maxLength * SYMBOL_WIDTH + 2 * FRAME_MARGIN);
        setFrameHeight((SYMBOL_HEIGHT + LINE_SPACING) //заголовок
                + SEPARATOR_HEIGHT
                + drawnSignatures.size() * (SYMBOL_HEIGHT + LINE_SPACING)
                + 2 * FRAME_MARGIN);
    }
}
