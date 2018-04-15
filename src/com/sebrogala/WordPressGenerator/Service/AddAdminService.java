package com.sebrogala.WordPressGenerator.Service;

import com.sebrogala.WordPressGenerator.Helper.FileHandler;
import com.sebrogala.WordPressGenerator.Helper.NameConverter;
import com.sebrogala.WordPressGenerator.Helper.PregReplaceInFile;
import com.sebrogala.WordPressGenerator.Helper.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

public class AddAdminService {
    String pluginPath;
    NameConverter converter;

    public AddAdminService(String pluginPath, String pluginFullName) {
        this.pluginPath = pluginPath;
        this.converter = new NameConverter(pluginFullName);
    }

    public AddAdminService(String pluginPath, NameConverter converter) {
        this.pluginPath = pluginPath;
        this.converter = converter;
    }

    public void run() {
        Map<String, String> vars = new HashMap<>();
        vars.put("capCamelName", converter.capitalizedCamelCase());

        TemplateRenderer.Render("Admin/PluginNameAdmin.php", vars, pluginPath + "/admin/" + converter.capitalizedCamelCase() + "Admin.php");
        TemplateRenderer.Render("Admin/service/PluginNameAdminMenuRouter.php", vars, pluginPath + "/admin/service/" + converter.capitalizedCamelCase() + "AdminMenuRouter.php");
        TemplateRenderer.Render("Admin/partial/PluginNameAdminDisplay.php", pluginPath + "/admin/partial/" + converter.capitalizedCamelCase() + "AdminDisplay.php");
        TemplateRenderer.Render("Admin/js/PluginNameAdmin.js", pluginPath + "/admin/js/" + converter.capitalizedCamelCase() + "Admin.js");
        TemplateRenderer.Render("Admin/css/PluginNameAdmin.css", pluginPath + "/admin/css/" + converter.capitalizedCamelCase() + "Admin.css");
        TemplateRenderer.Render("NewPlugin/silentIndex.php", pluginPath + "/admin/index.php");

        String configContent = "\n\t\t\\$this->defineAdminHooks();$1require_once \\$pluginDirPath . 'admin/service/" + converter.capitalizedCamelCase() + "AdminMenuRouter.php';\n\t\trequire_once \\$pluginDirPath . 'admin/" + converter.capitalizedCamelCase() + "Admin.php';\n\t\t";
        String regexp = "(\\s*\\}\\s*private function loadDependencies\\(\\)\\s*\\{\\s*[\\$\\w\\ \\=\\(\\)\\;]*\\s*)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", regexp, configContent);

        TemplateRenderer adminHooks = new TemplateRenderer("Partial/DefineAdminHooks.php", vars);
        String functionContent = "\n\n" + adminHooks.render().getRenderedTemplate() + "$1";
        String functionRegexp = "(\\s*public function run)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", functionRegexp, functionContent);

        FileHandler.refresh();
    }
}
