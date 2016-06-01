package com.jakutenshi.projects.umlplugin.ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class UMLToolWindowContentPanel extends SimpleToolWindowPanel {
    private final ActionManager actionManager;
    private final ActionToolbar actionToolbar;
    private UMLDiagramPanel drawingPanel;

    public UMLToolWindowContentPanel() {
        super(true, true);
        actionManager = ActionManager.getInstance();
        actionToolbar = actionManager.createActionToolbar(
                "toolbar",
                (ActionGroup) actionManager.getAction("UMLPlugin.UI.Toolbar"),
                true);
        setToolbar(actionToolbar.getComponent());

        drawingPanel =  new UMLDiagramPanel();
        JScrollPane scrollPane = new JScrollPane(drawingPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setContent(scrollPane);
    }

}
