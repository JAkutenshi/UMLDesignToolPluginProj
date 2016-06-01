package com.jakutenshi.projects.umlplugin.ui;

import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.draw.ClassDrawer;
import com.jakutenshi.projects.umlplugin.draw.EnumDrawer;
import com.jakutenshi.projects.umlplugin.draw.InterfaceDrawer;
import com.jakutenshi.projects.umlplugin.draw.UMLDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by JAkutenshi on 26.05.2016.
 */
public class UMLDiagramPanel extends JPanel {
    private DiagramContainer container = DiagramContainer.getInstance();


    public UMLDiagramPanel() {
        setLayout(null);
        setSize(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        HashMap<String, UMLEntity> drawnEntities = container.getUmlEntities();
        if (drawnEntities == null || drawnEntities.size() == 0) {
            return;
        }


        int currentX = 15;
        int currentY = 15;
        int space = 15;

        UMLEntity entity;
        UMLDrawer drawer = null;

        int maxDrawnEntityHeight = 0;
        int maxDrawnEntityWidth = 0;

        ArrayList<UMLDrawer> diagramDrawers = new ArrayList<>();
        for (String key : drawnEntities.keySet()) {
            entity = drawnEntities.get(key);
            if (entity instanceof Class) {
                drawer = new ClassDrawer(entity);
            } else if (entity instanceof Interface) {
                drawer = new InterfaceDrawer(entity);
            } else {
                drawer = new EnumDrawer(entity);
            }
            diagramDrawers.add(drawer);
            if (drawer.getFrameHeight() > maxDrawnEntityHeight) {
                maxDrawnEntityHeight = drawer.getFrameHeight();
            }
            if (drawer.getFrameWidth() > maxDrawnEntityWidth) {
                maxDrawnEntityWidth = drawer.getFrameWidth();
            }
        }

        int maxWidth = currentX * 2 + (maxDrawnEntityWidth + space * 2) * diagramDrawers.size();
        int maxHeight = currentY * 2 + (maxDrawnEntityHeight + space * 2) * diagramDrawers.size();

        setPreferredSize(new Dimension(maxWidth, maxHeight));


        int rows = 1 + (int) Math.sqrt(diagramDrawers.size());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (i * rows + j < diagramDrawers.size()) {
                    drawer = diagramDrawers.get(i * rows + j);
                    drawer.setX(currentX);
                    drawer.setY(currentY);
                    drawer.draw(g2);
                    currentX += maxDrawnEntityWidth + space;
                } else {
                    break;
                }
            }
            currentY += maxDrawnEntityHeight + space;
            currentX = 15;
        }
    }
}
