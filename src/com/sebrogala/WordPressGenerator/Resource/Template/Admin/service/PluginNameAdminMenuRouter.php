<?php

class {{ capCamelName }}AdminMenuRouter
{
    private $argumentName = "route";

    private $partialsFolder = '';

    public function __construct()
    {
        $this->partialsFolder = plugin_dir_path( __FILE__ ) . '../partial/';
    }

    public function routes()
    {
        return [
            [
                "route" => "default",
                "file" => '{{ capCamelName }}AdminDisplay',
            ],
        ];
    }

    public function getView($route)
    {
        return include_once $this->partialsFolder . $route['file'] . '.php';
    }

    public function execute()
    {
        foreach ($this->routes() as $route) {
            $page = $_GET[$this->argumentName];
            if ($page == $route['route']) {
                return $this->getView($route);
            }
        }

        //default route
        return $this->getView($this->routes()[0]);
    }
}
