package com.sebrogala.WordPressGenerator.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.sebrogala.WordPressGenerator.Helper.WordPressPluginStructure;
import com.sebrogala.WordPressGenerator.Service.AddPublicService;

public class AddPublic extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        WordPressPluginStructure wpStructure = new WordPressPluginStructure(e);
        AddPublicService service = new AddPublicService(wpStructure.clickedMenuItemPath(), wpStructure.pluginFullNameFromClickedPlugin());
        service.run();
    }

    public void update(AnActionEvent e) {
        WordPressPluginStructure.hideMenuItemIfNotClickedOnSpecificPlugin(e, "public");
    }
}
