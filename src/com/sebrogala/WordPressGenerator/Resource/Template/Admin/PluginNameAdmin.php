<?php

class {{ capCamelName }}Admin
{
    /** @var string $pluginName The ID of this plugin. */
    private $pluginName;

    /** @var string $version The current version of this plugin. */
    private $version;

    /**
     * @param string $pluginName The name of this plugin.
     * @param string $version The version of this plugin.
     */
    public function __construct($pluginName, $version)
    {
        $this->pluginName = $pluginName;
        $this->version = $version;
    }

    public function enqueueStyles($hook)
    {
        if ($hook != 'toplevel_page_' . $this->pluginName) {
            return;
        }

        wp_enqueue_style($this->pluginName, plugin_dir_url(__FILE__) . 'css/{{ capCamelName }}Admin.css', [], $this->version, 'all');
    }

    public function enqueueScripts($hook)
    {
        if ($hook != 'toplevel_page_' . $this->pluginName) {
            return;
        }

        wp_enqueue_script($this->pluginName, plugin_dir_url(__FILE__) . 'js/{{ capCamelName }}Admin.js', ['jquery'], $this->version, false);
    }

    public function getAdminView()
    {
        return include_once plugin_dir_path(__FILE__) . 'partial/{{ capCamelName }}AdminDisplay.php';
    }

    public function addMenuPage()
    {
        add_menu_page(
            'PageTitle',
            'MenuTitle',
            'manage_options',
            "menu-slug",
            [new {{ capCamelName }}AdminMenuRouter(), 'execute'],
            'icon',
            '58.001' //position
        );
    }
}
