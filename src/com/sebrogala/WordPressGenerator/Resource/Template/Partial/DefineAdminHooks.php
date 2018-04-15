    private function defineAdminHooks()
    {
        \$pluginAdmin = new {{ capCamelName }}Admin(\$this->getPluginName(), \$this->getVersion());

        \$this->loader->add_action('admin_enqueue_scripts', \$pluginAdmin, 'enqueueStyles');
        \$this->loader->add_action('admin_enqueue_scripts', \$pluginAdmin, 'enqueueScripts');
        \$this->loader->add_action('admin_menu', \$pluginAdmin, 'addMenuPage');
    }