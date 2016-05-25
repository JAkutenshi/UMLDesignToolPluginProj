package com.jakutenshi.projects.umlplugin.ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.ScrollPaneFactory;

import javax.swing.*;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class UMLDesignPanel extends SimpleToolWindowPanel {
    public UMLDesignPanel() {
        super(true, true);

        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar actionToolbar =
                actionManager.createActionToolbar("UML Plugin toolbar",
                        (ActionGroup) actionManager.getAction("UMLPlugin.UI.Toolbar"),
                        true);
        setToolbar(actionToolbar.getComponent());
        setContent(ScrollPaneFactory.createScrollPane(new JPanel()));
    }

}
