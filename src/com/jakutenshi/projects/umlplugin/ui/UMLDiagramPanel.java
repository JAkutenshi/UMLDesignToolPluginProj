package com.jakutenshi.projects.umlplugin.ui;

import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.draw.InterfaceDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by JAkutenshi on 26.05.2016.
 */
public class UMLDiagramPanel extends JPanel {
    DiagramContainer container = DiagramContainer.getInstance();

    public UMLDiagramPanel() {
        setLayout(null);
        setSize(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        HashMap<String, UMLEntity> drawnEntities = container.getUmlEntities();
        if (drawnEntities == null) {
            g2.drawChars("Nothing".toCharArray(), 0, 7, 10, 10);
            return;
        }

        UMLEntity drawnEntity;
        InterfaceDrawer interafceDrawer;

        int currentX = 10;
        int currentY = 10;
        int space = 10;
        for (String key : drawnEntities.keySet()) {
            drawnEntity = drawnEntities.get(key);
            if (drawnEntity instanceof Interface) {
                interafceDrawer = new InterfaceDrawer(currentX, currentY, drawnEntity);
                interafceDrawer.draw(g2);
                currentY += interafceDrawer.getFrameHeight() + space;
            }
        }
    }
}
