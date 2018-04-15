package com.sebrogala.WordPressGenerator.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.sebrogala.WordPressGenerator.Helper.WordPressPluginStructure;
import com.sebrogala.WordPressGenerator.Service.AddAdminService;

public class AddAdmin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        WordPressPluginStructure wpStructure = new WordPressPluginStructure(e);
        AddAdminService service = new AddAdminService(wpStructure.clickedMenuItemPath(), wpStructure.pluginFullNameFromClickedPlugin());
        service.run();
    }

    public void update(AnActionEvent e) {
        WordPressPluginStructure.hideMenuItemIfNotClickedOnSpecificPlugin(e, "admin");
    }
}
