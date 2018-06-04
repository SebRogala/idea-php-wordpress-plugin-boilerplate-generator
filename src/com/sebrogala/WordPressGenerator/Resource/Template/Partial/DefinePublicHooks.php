    private function definePublicHooks()
    {
        \$pluginPublic = new {{ capCamelName }}Public(\$this->getPluginName(), \$this->getVersion());

        \$this->loader->add_action('wp_enqueue_scripts', \$pluginPublic, 'enqueueStyles');
        \$this->loader->add_action('wp_enqueue_scripts', \$pluginPublic, 'enqueueScripts');
    }