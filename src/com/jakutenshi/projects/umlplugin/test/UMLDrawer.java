package com.jakutenshi.projects.umlplugin.test;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.awt.*;

/**
 * Created by JAkutenshi on 29.05.2016.
 */
public abstract class UMLDrawer {
    protected class DrawnLine {
        private Font font = new Font(DEFAULT_LINE_FONT,
                DEFAULT_LINE_FONT_STYLE,
                DEFAULT_LINE_FONT_SIZE);
        String line;

        public DrawnLine(Font font, String line) {
            this.font = font;
            this.line = line;
        }

        public DrawnLine(String line) {
            this.line = line;
        }

        public DrawnLine() {
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }
    }

    private int x;
    private int y;
    private int frameWidth;
    private int frameHeight;
    private final int offset;

    public final static int LINE_SPACING = 4;
    public final static int FRAME_MARGIN = 10;
    public final static int SEPARATOR_HEIGHT = LINE_SPACING * 2 + 1;
    public final static String DEFAULT_LINE_FONT = "Monospaced";
    public final static int DEFAULT_LINE_FONT_STYLE = Font.PLAIN;
    public final static int DEFAULT_LINE_FONT_SIZE = 14;
    public final static int SYMBOL_WIDTH = 9;
    public final static int SYMBOL_HEIGHT = 11;

    private DrawnLine drawnTitle;

    public abstract void draw(Graphics2D g);
    protected abstract void fillContained(UMLEntity entity);

    public UMLDrawer(int x, int y, UMLEntity entity) {
        this.x = x;
        this.y = y;
        offset = x + FRAME_MARGIN;
//заголовок
        drawnTitle = new DrawnLine(entity.titleToUML());
        if (entity.getKeywords().contains("abstract")) {
            drawnTitle.setFont(new Font(DEFAULT_LINE_FONT,
                    Font.ITALIC,
                    DEFAULT_LINE_FONT_SIZE));
        }

        /*if (entity instanceof Class) {
            Class aClass = (Class) entity;
//заголовок
            drawnTitle = aClass.titleToUML();
//поля
            drawableFields = new LinkedList<>();
            for (Field field : aClass.getFields()) {
                drawableFields.addFirst(field.toUML());
            }
//методы
            drawableMethods = new LinkedList<>();
            for (Method method : aClass.getMethods()) {
                drawableMethods.addFirst(method.toUML());
            }
        } else if (entity instanceof Interface) {
            Interface anInterface = (Interface) entity;
//заголовок
        } else {

        }*/
    }

    /**
     * @param y - откуда разделитель по У
     * @return откуда идет рисование дальше
     */
    protected int drawSeparator(int y, Graphics2D g) {
        g.drawLine(x, y + LINE_SPACING,
                x + frameWidth, y + LINE_SPACING);
        return y + SEPARATOR_HEIGHT + SYMBOL_HEIGHT;
    }

    public DrawnLine getDrawnTitle() {
        return drawnTitle;
    }

    public void setDrawnTitle(DrawnLine drawnTitle) {
        this.drawnTitle = drawnTitle;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOffset() {
        return offset;
    }
}
