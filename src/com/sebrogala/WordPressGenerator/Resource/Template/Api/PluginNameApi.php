<?php

class {{ capCamelName }}Api
{
    /** @var string $pluginName The ID of this plugin. */
    private $pluginName;

    /** @var string $version The current version of this plugin. */
    private $version;

    /**
     * @param string $pluginName The name of this plugin.
     * @param string $version The version of this plugin.
     */
    public function __construct( $pluginName, $version )
	{
		$this->pluginName = $pluginName;
		$this->version = $version;
	}

    public function registerRoutes()
    {
        $routes = include_once plugin_dir_path( __FILE__ ) . 'routes.php';
        foreach ($routes as $route) {
            register_rest_route(
                $route['namespace'],
                $route['route'],
                [
                    'methods' => $route['methods'],
                    'callback' => [
                        new $route['action'],
                        'execute'
                    ]
                ]
            );
        }
	}
}
