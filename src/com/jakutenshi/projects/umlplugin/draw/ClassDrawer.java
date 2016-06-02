package com.jakutenshi.projects.umlplugin.draw;

import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Field;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;
import com.jakutenshi.projects.umlplugin.draw.relationships.Generalisation;
import com.jakutenshi.projects.umlplugin.draw.relationships.UMLRelationDrawer;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by JAkutenshi on 31.05.2016.
 */
public class ClassDrawer extends UMLDrawer {
    private LinkedList<DrawnLine> drawnFields;
    private LinkedList<DrawnLine> drawnMethods;


    public ClassDrawer(UMLEntity entity) {
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
//поля
        currentY = drawSection(currentY, drawnFields, g);
//разделитель
        currentY = drawSeparator(currentY, g);
//методы
        drawSection(currentY, drawnMethods, g);


}

    @Override
    protected void fillContent(UMLEntity entity) {
        Class aClass = (Class) entity;

        int maxLength = getDrawnTitle().getLine().length();
//поля
        drawnFields = new LinkedList<>();
        for(Field field : aClass.getFields()) {
            drawnFields.addFirst(new DrawnLine(field.toUML()));
            if (drawnFields.getFirst().getLine().length() > maxLength) {
                maxLength = drawnFields.getFirst().getLine().length();
            }
            if (field.getKeywords().contains(Keyword.STATIC)) {
                makeFontUnderlined(drawnFields.getFirst());
            }
        }
//методы
        drawnMethods = new LinkedList<>();
        for (Method method : aClass.getMethods()) {
            drawnMethods.addFirst(new DrawnLine(method.toUML()));
            if (drawnMethods.getFirst().getLine().length() > maxLength) {
                maxLength = drawnMethods.getFirst().getLine().length();
            }
            if (method.getKeywords().contains(Keyword.STATIC)) {
               makeFontUnderlined(drawnMethods.getFirst());
            } else if (method.getKeywords().contains(Keyword.ABSTRACT)) {
                drawnMethods.getFirst().setFont(ITALIC_FONT);
            }
        }

        setFrameWidth(maxLength * SYMBOL_WIDTH + 2 * FRAME_MARGIN);
        setFrameHeight((SYMBOL_HEIGHT + LINE_SPACING) //заголовок
                + SEPARATOR_HEIGHT * 2
                + (drawnFields.size() ==  0 ? 1 : drawnFields.size()) * DRAWN_LINE_HEIGHT
                + (drawnMethods.size() ==  0 ? 1 : drawnMethods.size()) * DRAWN_LINE_HEIGHT
                + 2 * FRAME_MARGIN);
    }

    @Override
    public void fillRelations(UMLEntity entity) {
        Class aClass = (Class) entity;
        if (aClass.getExtendsClass() != null) {
            Generalisation generalisationArrow = new Generalisation();
        }
    }
}
