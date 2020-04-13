package com.taylor.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.taylor.sentinel.config.DefaultBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello sentinel";
    }

    @GetMapping("/resource")
    @SentinelResource(value = "resource", blockHandler = "exHandler")
    public String resource() {
        return "resource";
    }

    public String exHandler(BlockException e) {
        return "WelcomeController.exHandler error";
    }

    @GetMapping("/resource2")
    @SentinelResource(value = "resource2", blockHandler = "exHandler", blockHandlerClass = {DefaultBlockHandler.class})
    public String resource2() {
        return "resource2";
    }
}
