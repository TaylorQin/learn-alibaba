package com.learn.controller;

import com.learn.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private EchoService echoService;

    @GetMapping("/echo/{name}")
    String echo(@PathVariable("name") String str) {
        return echoService.echo(appName+" "+str);
    }

}
