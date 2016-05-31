package com.jakutenshi.projects.umlplugin.draw;

import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by JAkutenshi on 29.05.2016.
 */
public class InterfaceDrawer extends UMLDrawer {
    private LinkedList<DrawnLine> drawnSignatures;

    public InterfaceDrawer(int x, int y, UMLEntity entity) {
        super(x, y, entity);
        fillContained(entity);
    }

    @Override
    public void draw(Graphics2D g) {
        int yCurrent = 0;

//рисуем прямогугольник
        g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), getFrameWidth(), getFrameHeight());
//рамку вокруг
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getFrameWidth(), getFrameHeight());
//заголовок
        yCurrent = getY() + FRAME_MARGIN + SYMBOL_HEIGHT;
        g.setFont(getDrawnTitle().getFont());
        g.drawChars(getDrawnTitle().getLine().toCharArray(),
                0,
                getDrawnTitle().getLine().length(),
                getOffset(),
                yCurrent);
        yCurrent += SYMBOL_HEIGHT;
//разделитель
        yCurrent = drawSeparator(yCurrent, g);
//сигнатуры
        for (DrawnLine line : drawnSignatures) {
            g.setFont(line.getFont());
            g.drawChars(line.getLine().toCharArray(),
                    0,
                    line.getLine().length(),
                    getOffset(),
                    yCurrent);
            yCurrent += SYMBOL_HEIGHT + LINE_SPACING;
        }
    }

    @Override
    public void fillContained(UMLEntity entity) {
        Interface anInterface = (Interface) entity;
        drawnSignatures = new LinkedList<>();
        String signatureString;
        DrawnLine newDrawnLine;
        Map fontAttributes;
        int maxLength = getDrawnTitle().getLine().length(); //длина строки для вычисления ширины

        for (Method signature : anInterface.getSignatures()) {
            signatureString = signature.toUML();
            newDrawnLine = new DrawnLine(signatureString);
            if (signature.getKeywords().contains("static")) {
                fontAttributes = newDrawnLine.getFont().getAttributes();
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }
            drawnSignatures.addFirst(newDrawnLine);
            if (signatureString.length() > maxLength) {
                maxLength = signatureString.length();
            }
        }

        setFrameWidth(maxLength * SYMBOL_WIDTH + 2 * FRAME_MARGIN);
        setFrameHeight((SYMBOL_HEIGHT + LINE_SPACING) //заголовок
                + SEPARATOR_HEIGHT
                + drawnSignatures.size() * (SYMBOL_HEIGHT + LINE_SPACING)
                + 2 * FRAME_MARGIN);
    }
}
