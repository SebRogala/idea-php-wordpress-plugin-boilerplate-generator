package com.sebrogala.WordPressGenerator.Helper;

import java.io.InputStream;
import java.util.Map;

public class TemplateRenderer {

    private String templateName;
    private Map<String, String> vars;

    private String renderedTemplate;
    private boolean isRendered = false;

    public TemplateRenderer(String templateName, Map<String, String> vars) {
        this.templateName = templateName;
        this.vars = vars;
    }

    public TemplateRenderer(String templateName) {
        this.templateName = templateName;
    }


    public TemplateRenderer render() {
        InputStream in = getClass().getResourceAsStream("/com/sebrogala/WordPressGenerator/Resource/Template/" + templateName);
        String content = FileHandler.getContents(in);

        if(vars != null) {
            for (Map.Entry<String, String> entry : vars.entrySet()) {
                String regex = "\\{\\{\\s*?" + entry.getKey() + "\\s*?}}";
                content = content.replaceAll(regex, entry.getValue());
            }
        }

        renderedTemplate = content;
        isRendered = true;

        return this;
    }

    public TemplateRenderer rerender(Map<String, String> vars) {
        String content = renderedTemplate;

        for (Map.Entry<String, String> entry : vars.entrySet()) {
            String regex = "\\{\\{\\s*?" + entry.getKey() + "\\s*?}}";
            content = content.replaceAll(regex, entry.getValue());
        }

        renderedTemplate = content;

        return this;
    }

    public String getRenderedTemplate() {
        if(!isRendered){
            throw new RuntimeException();
        }

        return renderedTemplate;
    }

    public void saveRenderedFile(String path) {
        FileHandler.putContents(path, renderedTemplate);
    }

    public static void Render(String templatePath, Map<String, String> vars, String saveFilePath) {
        TemplateRenderer rend = new TemplateRenderer(templatePath, vars);
        rend.render().saveRenderedFile(saveFilePath);
    }

    public static void Render(String templatePath, String saveFilePath) {
        TemplateRenderer rend = new TemplateRenderer(templatePath);
        rend.render().saveRenderedFile(saveFilePath);
    }
}
