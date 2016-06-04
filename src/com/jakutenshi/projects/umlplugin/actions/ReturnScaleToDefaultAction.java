package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jakutenshi.projects.umlplugin.ui.UMLDiagramPanel;
import com.jakutenshi.projects.umlplugin.ui.UMLToolWindowContentPanel;

/**
 * Created by JAkutenshi on 04.06.2016.
 */
public class ReturnScaleToDefaultAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        UMLDiagramPanel panel = UMLToolWindowContentPanel.getDrawingPanel();
        if (panel != null) {
            panel.returnScaleToDefault();
        }
    }
}
