package com.sebrogala.WordPressGenerator.Helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class WordPressPluginStructure {

    private VirtualFile clickedMenuItem;

    public WordPressPluginStructure(AnActionEvent e) {
        clickedMenuItem = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
    }

    public String clickedMenuItemPath() {
        return clickedMenuItem.getCanonicalPath();
    }

    public String clickedMenuItemParentPath() {
        return clickedMenuItem.getParent().getCanonicalPath();
    }

    public String clickedMenuItemName() {
        return clickedMenuItem.getCanonicalPath().replaceAll(".+\\/([\\w-]+$)", "$1");
    }

    public String pluginFullNameFromClickedPlugin()
    {
        String content = FileHandler.getContents(clickedMenuItemPath() + "/" + clickedMenuItemName() + ".php");

        return StringUtility.findByRegExp(content, "\\*\\s*?Plugin\\ Name:\\s*([\\w\\ ]+)");
    }

    public static void hideMenuItemIfNotClickedOnPluginsDirectory(AnActionEvent e) {
        VirtualFile clickedMenuItem = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        String clickedName = clickedMenuItem.getCanonicalPath().replaceAll(".+\\/(\\w+$)", "$1");
        if(!"plugins".equals(clickedName)) {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }

    public static void hideMenuItemIfNotClickedOnSpecificPlugin(AnActionEvent e, String name) {
        VirtualFile clickedMenuItem = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        String clickedParentName = clickedMenuItem.getParent().getCanonicalPath().replaceAll(".+\\/(\\w+$)", "$1");
        Boolean specificFolderAlreadyExists = new File(clickedMenuItem.getCanonicalPath() + "/" + name).exists();

        if(!"plugins".equals(clickedParentName) || specificFolderAlreadyExists) {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
}
