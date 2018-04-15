<?php
/**
 * Plugin Name:       {{ FullName }}
 * Description:       {{ Description }}
 * Version:           {{ Version }}
 * Author:            {{ Author }}
 */

if ( ! defined( 'WPINC' ) ) {
	die;
}

define( 'PLUGIN_{{ capitalized_name }}', '1.0.0' );

function activate{{ capCamelName }}() {
	require_once plugin_dir_path( __FILE__ ) . 'includes/{{ capCamelName }}Activator.php';
    {{ capCamelName }}Activator::activate();
}

function deactivate{{ capCamelName }}() {
	require_once plugin_dir_path( __FILE__ ) . 'includes/{{ capCamelName }}Deactivator.php';
    {{ capCamelName }}Deactivator::deactivate();
}

register_activation_hook( __FILE__, 'activate{{ capCamelName }}' );
register_deactivation_hook( __FILE__, 'deactivate{{ capCamelName }}' );

require plugin_dir_path( __FILE__ ) . 'includes/{{ capCamelName }}.php';

(new {{ capCamelName }}())->run();