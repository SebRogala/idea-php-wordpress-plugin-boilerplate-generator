<?php

class {{ capCamelName }}
{
	/** @var {{ capCamelName }}Loader $loader Maintains and registers all hooks for the plugin. */
	protected $loader;

	/** @var string $pluginName The string used to uniquely identify this plugin. */
	protected $pluginName;

	/** @var string $version The current version of the plugin. */
	protected $version;

	public function __construct() {
		if ( defined( '{{ capitalized_name }}_VERSION' ) ) {
			$this->version = {{ capitalized_name }}_VERSION;
		} else {
			$this->version = '1.0.0';
		}
		$this->pluginName = '{{ slugName }}';

		$this->loadDependencies();
	}

	private function loadDependencies()
    {
        $pluginDirPath = plugin_dir_path(dirname(__FILE__));

		require_once $pluginDirPath . 'includes/{{ capCamelName }}Loader.php';

		$this->loader = new {{ capCamelName }}Loader();
	}

	public function run()
    {
		$this->loader->run();
	}

	public function getPluginName()
    {
		return $this->pluginName;
	}

	public function getLoader()
    {
		return $this->loader;
	}

	public function getVersion()
    {
		return $this->version;
	}

}
