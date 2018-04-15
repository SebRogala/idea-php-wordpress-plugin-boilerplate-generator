package com.sebrogala.WordPressGenerator.Service;

import com.sebrogala.WordPressGenerator.Helper.FileHandler;
import com.sebrogala.WordPressGenerator.Helper.NameConverter;
import com.sebrogala.WordPressGenerator.Helper.PregReplaceInFile;
import com.sebrogala.WordPressGenerator.Helper.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

public class AddApiService {
    String pluginPath;
    NameConverter converter;

    public AddApiService(String pluginPath, String pluginFullName) {
        this.pluginPath = pluginPath;
        this.converter = new NameConverter(pluginFullName);
    }

    public AddApiService(String pluginPath, NameConverter converter) {
        this.pluginPath = pluginPath;
        this.converter = converter;
    }

    public void run() {
        Map<String, String> vars = new HashMap<>();
        vars.put("capCamelName", converter.capitalizedCamelCase());
        vars.put("slugName", converter.slug());

        TemplateRenderer.Render("Api/PluginNameApi.php", vars, pluginPath + "/api/" + converter.capitalizedCamelCase() + "Api.php");
        TemplateRenderer.Render("Api/routes.php", vars, pluginPath + "/api/routes.php");
        TemplateRenderer.Render("Api/action/Ping.php", pluginPath + "/api/action/Ping.php");
        TemplateRenderer.Render("NewPlugin/silentIndex.php", pluginPath + "/api/index.php");

        String configContent = "\n\t\t\\$this->defineApiHooks();$1require_once \\$pluginDirPath . 'api/" + converter.capitalizedCamelCase() + "Api.php';\n\t\t";
        String regexp = "(\\s*\\}\\s*private function loadDependencies\\(\\)\\s*\\{\\s*[\\$\\w\\ \\=\\(\\)\\;]*\\s*)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", regexp, configContent);

        TemplateRenderer adminHooks = new TemplateRenderer("Partial/DefineApiHooks.php", vars);
        String functionContent = "\n\n" + adminHooks.render().getRenderedTemplate() + "$1";
        String functionRegexp = "(\\s*public function run)";
        PregReplaceInFile.run(pluginPath + "/includes/" + converter.capitalizedCamelCase() + ".php", functionRegexp, functionContent);

        FileHandler.refresh();
    }
}
