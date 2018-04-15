<?php

class Ping
{
    public function execute(WP_REST_Request $request)
    {
        return new WP_REST_Response(["ack" => time()]);
    }
}
