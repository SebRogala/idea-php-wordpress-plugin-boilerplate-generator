package com.sebrogala.WordPressGenerator.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.sebrogala.WordPressGenerator.Form.NewPluginForm;
import com.sebrogala.WordPressGenerator.Helper.FileHandler;
import com.sebrogala.WordPressGenerator.Helper.NameConverter;
import com.sebrogala.WordPressGenerator.Helper.TemplateRenderer;
import com.sebrogala.WordPressGenerator.Helper.WordPressPluginStructure;
import com.sebrogala.WordPressGenerator.Service.AddAdminService;
import com.sebrogala.WordPressGenerator.Service.AddApiService;
import com.sebrogala.WordPressGenerator.Service.AddPublicService;
import com.sebrogala.WordPressGenerator.ViewModel.NewPluginViewModel;

import java.util.HashMap;
import java.util.Map;

public class NewPlugin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        NewPluginViewModel model = NewPluginForm.run("New WordPress Plugin");
        if("".equals(model.pluginName)) {
            return;
        }
        WordPressPluginStructure wpStructure = new WordPressPluginStructure(e);
        NameConverter nameConverter = new NameConverter(model.pluginName);
        String pluginPath = wpStructure.clickedMenuItemPath() + "/" + nameConverter.slug();

        Map<String, String> vars = new HashMap<>();
        vars.put("capitalized_name", nameConverter.capitalizedUnderscores());
        vars.put("capCamelName", nameConverter.capitalizedCamelCase());
        vars.put("slugName", nameConverter.slug());

        Map<String, String> pluginInfo = new HashMap<>();
        pluginInfo.put("FullName", model.pluginName);
        pluginInfo.put("Description", model.description);
        pluginInfo.put("Version", model.version);
        pluginInfo.put("Author", model.author);

        TemplateRenderer pluginName = new TemplateRenderer("NewPlugin/PluginName.php", vars);
        pluginName.render()
                .rerender(pluginInfo)
                .saveRenderedFile(pluginPath + "/" + nameConverter.slug() + ".php");

        TemplateRenderer includesPluginName = new TemplateRenderer("NewPlugin/includes/PluginName.php", vars);
        includesPluginName.render()
                .saveRenderedFile(pluginPath + "/includes/" + nameConverter.capitalizedCamelCase() + ".php");

        TemplateRenderer includesPluginNameActivator = new TemplateRenderer("NewPlugin/includes/PluginNameActivator.php", vars);
        includesPluginNameActivator.render()
                .saveRenderedFile(pluginPath + "/includes/" + nameConverter.capitalizedCamelCase() + "Activator.php");

        TemplateRenderer includesPluginNameDeactivator = new TemplateRenderer("NewPlugin/includes/PluginNameDeactivator.php", vars);
        includesPluginNameDeactivator.render()
                .saveRenderedFile(pluginPath + "/includes/" + nameConverter.capitalizedCamelCase() + "Deactivator.php");

        TemplateRenderer includesPluginNameLoader = new TemplateRenderer("NewPlugin/includes/PluginNameLoader.php", vars);
        includesPluginNameLoader.render()
                .saveRenderedFile(pluginPath + "/includes/" + nameConverter.capitalizedCamelCase() + "Loader.php");

        TemplateRenderer silentIndex = new TemplateRenderer("NewPlugin/silentIndex.php");
        silentIndex.render()
                .saveRenderedFile(pluginPath + "/index.php");
        silentIndex.render()
                .saveRenderedFile(pluginPath + "/includes/index.php");

        TemplateRenderer uninstall = new TemplateRenderer("NewPlugin/uninstall.php");
        uninstall.render()
                .saveRenderedFile(pluginPath + "/uninstall.php");

        if(model.addAdmin) {
            AddAdminService service = new AddAdminService(pluginPath, nameConverter);
            service.run();
        }

        if(model.addApi) {
            AddApiService service = new AddApiService(pluginPath, nameConverter);
            service.run();
        }

        if(model.addPublic) {
            AddPublicService service = new AddPublicService(pluginPath, nameConverter);
            service.run();
        }

        FileHandler.refresh();
    }

    public void update(AnActionEvent e) {
        WordPressPluginStructure.hideMenuItemIfNotClickedOnPluginsDirectory(e);
    }
}
