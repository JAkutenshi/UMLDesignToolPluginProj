package com.jakutenshi.projects.umlplugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;



/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class UMLPluginToolWindowFactory implements ToolWindowFactory {
    private ToolWindow UMLPluginToolWindow;
    private UMLToolWindowContentPanel panel;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        panel = new UMLToolWindowContentPanel();
        UMLPluginToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", true);
        toolWindow.getContentManager().addContent(content);
    }
}
