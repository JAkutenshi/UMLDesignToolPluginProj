package com.jakutenshi.projects.umlplugin.drawers;

import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
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
        currentY = drawTitle(currentY, g);
//разделитель
        currentY = drawSeparator(currentY, g);
//сигнатуры
        drawSection(currentY, drawnSignatures, g);
//генерики
        if (generics != null) {
            generics.draw(g);
        }
    }

    @Override
    public void fillContent(UMLEntity entity) {
        Interface anInterface = (Interface) entity;
        drawnSignatures = new LinkedList<>();

        int maxLength = getDrawnTitle().getLine().length(); //длина строки для вычисления ширины

        for (Method signature : anInterface.getSignatures()) {
            drawnSignatures.addFirst(new DrawnLine(signature.toUML()));
            if (signature.getKeywords().contains(Keyword.STATIC)) {
                makeFontUnderlined(drawnSignatures.getFirst());
            }
            if (drawnSignatures.getFirst().getLine().length() > maxLength) {
                maxLength = drawnSignatures.getFirst().getLine().length();
            }
        }
//генерики
        if (anInterface.getTypeParameters().size() != 0) {
            generics = new TypeParameterDrawer(anInterface);
        }

        setFrameWidth(maxLength * SYMBOL_WIDTH + 2 * FRAME_MARGIN);
        setFrameHeight((SYMBOL_HEIGHT + LINE_SPACING) //заголовок
                + SEPARATOR_HEIGHT
                + (drawnSignatures.size() ==  0 ? 1 : drawnSignatures.size())
                * (SYMBOL_HEIGHT + LINE_SPACING)
                + 2 * FRAME_MARGIN);
        if (generics != null) {
            setFrameWidth(getFrameWidth() + generics.getFrameWidth() - FRAME_MARGIN * 3);
            setFrameHeight(getFrameHeight() + generics.getFrameHeight() - FRAME_MARGIN);
        }
    }
}
