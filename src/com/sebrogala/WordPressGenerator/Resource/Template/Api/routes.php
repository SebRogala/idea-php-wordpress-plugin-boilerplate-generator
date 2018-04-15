<?php

include_once plugin_dir_path( __FILE__ ) . 'action/Ping.php';

$namespace = '{{ slugName }}';

return [
    [
        'namespace' => $namespace,
        'route' => '/ping',
        'methods' => 'GET',
        'action' => "Ping",
    ],
];
