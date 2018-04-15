    private function defineApiHooks()
    {
        \$pluginApi = new {{ capCamelName }}Api(\$this->getPluginName(), \$this->getVersion());

        \$this->loader->add_action('rest_api_init', \$pluginApi, 'registerRoutes');
    }