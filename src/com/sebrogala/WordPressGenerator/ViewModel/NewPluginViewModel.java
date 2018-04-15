package com.sebrogala.WordPressGenerator.ViewModel;

public class NewPluginViewModel {
    public String pluginName;
    public String version;
    public String author;
    public String description;
    public Boolean addAdmin;
    public Boolean addApi;
    public Boolean addPublic;

    public NewPluginViewModel(String pluginName, String version, String author, String description, Boolean addAdmin, Boolean addApi, Boolean addPublic) {
        this.pluginName = pluginName;
        this.version = version;
        this.author = author;
        this.description = description;
        this.addAdmin = addAdmin;
        this.addApi = addApi;
        this.addPublic = addPublic;
    }
}
