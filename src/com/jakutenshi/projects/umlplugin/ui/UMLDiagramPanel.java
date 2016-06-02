package com.jakutenshi.projects.umlplugin.ui;

import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.draw.ClassDrawer;
import com.jakutenshi.projects.umlplugin.draw.EnumDrawer;
import com.jakutenshi.projects.umlplugin.draw.InterfaceDrawer;
import com.jakutenshi.projects.umlplugin.draw.UMLDrawer;
import com.jakutenshi.projects.umlplugin.draw.relationships.Generalisation;
import com.jakutenshi.projects.umlplugin.draw.relationships.UMLRelationDrawer;
import com.jakutenshi.projects.umlplugin.util.UMLDiagramContainerObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JAkutenshi on 26.05.2016.
 */
public class UMLDiagramPanel extends JPanel implements UMLDiagramContainerObserver {
    private UMLDrawer draggedDrawer;
    private int startDragX;
    private int startDragY;
    private HashMap<String, UMLDrawer> drawers = new HashMap<>();
    private ArrayList<UMLRelationDrawer> arrows = new ArrayList<>();
    private int maxDrawnEntityHeight;
    private int maxDrawnEntityWidth;
    private int currentX;
    private int currentY;

    private final int SPACE = 10;

    public UMLDiagramPanel() {
        setLayout(null);
        setSize(500, 500);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedDrawer != null) {
                    if (0 <= e.getX() && e.getX() + draggedDrawer.getFrameWidth() / 2 <= getSize().width
                            && 0 <= e.getY() && e.getY() + draggedDrawer.getFrameHeight() / 2 <= getSize().height) {
                        draggedDrawer.setAnchorX(e.getX());
                        draggedDrawer.setAnchorY(e.getY());
                        repaint();
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getIt(e.getX(), e.getY(), drawers);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedDrawer = null;
            }
        });

        DiagramContainer.getInstance().addObserver(this);
    }

    private boolean getIt(int x, int y, HashMap<String, UMLDrawer> drawers) {
        UMLDrawer drawer;
        for (String key : drawers.keySet()) {
            drawer = drawers.get(key);
            if (isInFrame(x, y, drawer)) {
                draggedDrawer = drawer;
                startDragX = drawer.getX();
                startDragY = drawer.getY();
                return true;
            }
        }
        return false;
    }

    private boolean isInFrame(int x, int y, UMLDrawer drawer) {
        if (drawer.getX() <= x && x <= drawer.getX() + drawer.getFrameWidth()
                && drawer.getY() <= y && y <= drawer.getY() + drawer.getFrameHeight()) {
            return true;
        }

        return false;
    }

    private void clearPane(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        clearPane(g);

        if (drawers.size() == 0) {
            return;
        }

        for (UMLRelationDrawer arrow : arrows) {
            arrow.drawArrow(g);
        }
        for (String key : drawers.keySet()) {
            drawers.get(key).draw(g);
        }

    }

    @Override
    public void onChange(HashMap<String, UMLEntity> entities) {
        fillDrawnEntities(entities);
        repaint();
    }

    private void fillDrawnEntities(HashMap<String, UMLEntity> entities) {
        UMLEntity entity;
        UMLDrawer drawer;
        drawers = new HashMap<>();

        for (String key : entities.keySet()) {
            entity = entities.get(key);
            if (entity instanceof Class) {
                drawer = new ClassDrawer(entity);
            } else if (entity instanceof Interface) {
                drawer = new InterfaceDrawer(entity);
            } else {
                drawer = new EnumDrawer(entity);
            }
            drawer.setKey(entity.getPackagePath());
            drawers.put(drawer.getKey(), drawer);
            if (drawer.getFrameHeight() > maxDrawnEntityHeight) {
                maxDrawnEntityHeight = drawer.getFrameHeight();
            }
            if (drawer.getFrameWidth() > maxDrawnEntityWidth) {
                maxDrawnEntityWidth = drawer.getFrameWidth();
            }
        }

        int maxWidth = currentX * 2 + (maxDrawnEntityWidth + SPACE * 2) * drawers.size();
        int maxHeight = currentY * 2 + (maxDrawnEntityHeight + SPACE * 2) * drawers.size();

        setPreferredSize(new Dimension(maxWidth, maxHeight));

        fillCoordinates();
        createsRelations(entities);
    }

    private void fillCoordinates() {
        UMLDrawer drawer;
        currentX = SPACE;
        currentY = SPACE;
        int rows = 1 + (int) Math.sqrt(drawers.size());
        Object[] keys = drawers.keySet().toArray();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (i * rows + j < drawers.size()) {
                    drawer = drawers.get(keys[i * rows + j]);
                    drawer.setX(currentX);
                    drawer.setY(currentY);
                    currentX += maxDrawnEntityWidth + SPACE;
                } else {
                    break;
                }
            }
            currentY += maxDrawnEntityHeight + SPACE;
            currentX = 15;
        }
    }

    private void createsRelations(HashMap<String, UMLEntity> entities) {
        UMLEntity entity;
        UMLEntity targetEntity;
        UMLDrawer drawer;
        UMLDrawer targetDrawer;
        UMLRelationDrawer arrow;
        Class aClass;
        Interface anIneInterface;
        Enum anEnum;

        for (String key : drawers.keySet()) {
            drawer = drawers.get(key);
            entity = entities.get(key);

            if (entity instanceof Class) {
                aClass = (Class) entity;
                if (aClass.getExtendsClass() != null){
                    arrow = new Generalisation();
                    if (entities.containsKey(aClass.getExtendsClass())) {
                        targetEntity = entities.get(aClass.getExtendsClass());
                        targetDrawer = drawers.get(targetEntity.getPackagePath());
                        arrow.setStartKey(drawer.getKey());
                        arrow.setStart(new Point(drawer.getAnchorX(), drawer.getAnchorY()));
                        arrow.setEndKey(targetDrawer.getKey());
                        arrow.setEnd(new Point(targetDrawer.getAnchorX(), targetDrawer.getAnchorY()));
                        drawer.addObserver(arrow);
                        targetDrawer.addObserver(arrow);
                        arrows.add(arrow);
                    }
                }
            } else if (entity instanceof Interface) {
                drawer = new InterfaceDrawer(entity);
            } else {
                drawer = new EnumDrawer(entity);
            }

        }
    }
}
