<?php

class {{ capCamelName }}Public
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

	public function enqueueStyles()
    {
		wp_enqueue_style( $this->pluginName, plugin_dir_url( __FILE__ ) . 'css/{{ capCamelName }}Public.css', array(), $this->version, 'all' );
	}

	public function enqueueScripts()
    {
		wp_enqueue_script( $this->pluginName, plugin_dir_url( __FILE__ ) . 'js/{{ capCamelName }}Public.js', array( 'jquery' ), $this->version, false );
	}
}
