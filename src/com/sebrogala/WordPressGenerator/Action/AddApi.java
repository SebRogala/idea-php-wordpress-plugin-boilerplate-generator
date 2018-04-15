package com.sebrogala.WordPressGenerator.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.sebrogala.WordPressGenerator.Helper.WordPressPluginStructure;
import com.sebrogala.WordPressGenerator.Service.AddApiService;

public class AddApi extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        WordPressPluginStructure wpStructure = new WordPressPluginStructure(e);
        AddApiService service = new AddApiService(wpStructure.clickedMenuItemPath(), wpStructure.pluginFullNameFromClickedPlugin());
        service.run();
    }

    public void update(AnActionEvent e) {
        WordPressPluginStructure.hideMenuItemIfNotClickedOnSpecificPlugin(e, "api");
    }
}
