package com.jakutenshi.projects.umlplugin.draw;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.LinkedList;
import java.util.Map;

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
    private int offset;

    public final static int LINE_SPACING = 4;
    public final static int FRAME_MARGIN = 10;
    public final static int SEPARATOR_HEIGHT = LINE_SPACING * 2 + 1;
    public final static String DEFAULT_LINE_FONT = "Monospaced";
    public final static int DEFAULT_LINE_FONT_STYLE = Font.PLAIN;
    public final static int DEFAULT_LINE_FONT_SIZE = 14;
    public final static int SYMBOL_WIDTH = 9;
    public final static int SYMBOL_HEIGHT = 10;
    protected final static Font ITALIC_FONT = new Font(DEFAULT_LINE_FONT,
                                                          Font.ITALIC,
                                                          DEFAULT_LINE_FONT_SIZE);
    public final static int DRAWN_LINE_HEIGHT = SYMBOL_HEIGHT + LINE_SPACING;

    private DrawnLine drawnTitle;

    public abstract void draw(Graphics2D g);
    protected abstract void fillContent(UMLEntity entity);

    public UMLDrawer(UMLEntity entity) {

//заголовок
        drawnTitle = new DrawnLine(entity.titleToUML());
        if (entity.getKeywords().contains("abstract")) {
            drawnTitle.setFont(ITALIC_FONT);
        }
        fillContent(entity);
    }

    protected int drawSection(int y, LinkedList<DrawnLine> lines, Graphics2D g) {
        if (lines.size() == 0) {
            y += SYMBOL_HEIGHT;
        }
        for (DrawnLine line : lines) {
            y = drawLine(y, line, g);
        }
        return y - SYMBOL_HEIGHT;
    }

    protected int drawTitle(int y, Graphics2D g) {
        drawLine(y, getDrawnTitle(), g);
        return y + LINE_SPACING;
    }

    protected int drawLine(int y, DrawnLine line, Graphics2D g) {
        g.setFont(line.getFont());
        g.drawChars(line.getLine().toCharArray(),
                0,
                line.getLine().length(),
                getOffset(),
                y);
        return y + DRAWN_LINE_HEIGHT;
    }

    protected void drawFrame(Graphics2D g) {
//рисуем прямогугольник
        g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), getFrameWidth(), getFrameHeight());
//рамку вокруг
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getFrameWidth(), getFrameHeight());
    }

    protected int drawSeparator(int y, Graphics2D g) {
        g.drawLine(x, y + LINE_SPACING,
                x + frameWidth, y + LINE_SPACING);
        return y + SEPARATOR_HEIGHT + SYMBOL_HEIGHT;
    }

    protected void makeFontUnderlined(Font font) {
        Map fontAttributes = font.getAttributes();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    }

    protected DrawnLine getDrawnTitle() {
        return drawnTitle;
    }

    protected void setDrawnTitle(DrawnLine drawnTitle) {
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
        offset = x + FRAME_MARGIN;
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
