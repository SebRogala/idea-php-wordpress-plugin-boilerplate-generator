package com.sebrogala.WordPressGenerator.Service;

import com.sebrogala.WordPressGenerator.Helper.FileHandler;
import com.sebrogala.WordPressGenerator.Helper.NameConverter;
import com.sebrogala.WordPressGenerator.Helper.PregReplaceInFile;
import com.sebrogala.WordPressGenerator.Helper.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

public class AddPublicService {
    String pluginPath;
    NameConverter converter;

    public AddPublicService(String pluginPath, String pluginFullName) {
        this.pluginPath = pluginPath;
        this.converter = new NameConverter(pluginFullName);
    }

    public AddPublicService(String pluginPath, NameConverter converter) {
        this.pluginPath = pluginPath;
        this.converter = converter;
    }

    public void run() {
        Map<String, String> vars = new HashMap<>();
        vars.put("capCamelName", converter.capitalizedCamelCase());

        TemplateRenderer.Render("Public/PluginNamePublic.php", vars, pluginPath + "/public/" + converter.capitalizedCamelCase() + "Public.php");
        TemplateRenderer.Render("Public/partial/PluginNamePublicDisplay.php", pluginPath + "/public/partial/" + converter.capitalizedCamelCase() + "PublicDisplay.php");
        TemplateRenderer.Render("Public/js/PluginNamePublic.js", pluginPath + "/public/js/" + converter.capitalizedCamelCase() + "Public.js");
        TemplateRenderer.Render("Public/css/PluginNamePublic.css", pluginPath + "/public/css/" + converter.capitalizedCamelCase() + "Public.css");
        TemplateRenderer.Render("NewPlugin/silentIndex.php", pluginPath + "/public/index.php");

        String configContent = "\n\t\t\\$this->definePublicHooks();$1require_once \\$pluginDirPath . 'public/" + converter.capitalizedCamelCase() + "Public.php';\n\t\t";
        String regexp = "(\\s*\\}\\s*private function loadDependencies\\(\\)\\s*\\{\\s*[\\$\\w\\ \\=\\(\\)\\;]*\\s*)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", regexp, configContent);

        TemplateRenderer publicHooks = new TemplateRenderer("Partial/DefinePublicHooks.php", vars);
        String functionContent = "\n\n" + publicHooks.render().getRenderedTemplate() + "$1";
        String functionRegexp = "(\\s*public function run)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", functionRegexp, functionContent);

        FileHandler.refresh();
    }
}
