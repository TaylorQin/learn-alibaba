package com.taylor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @Value("${server.port}")
    private String port;


    @GetMapping(value = "/echo")
    public String echo(@RequestParam("name") String str) {
        return "\nHello " + str + " from " + port;
    }

    @GetMapping(value = "/echo/{str}")
    public String echoPathVariable(@PathVariable String str) {
        return "\nHello " + str + " from " + port;
    }

}
