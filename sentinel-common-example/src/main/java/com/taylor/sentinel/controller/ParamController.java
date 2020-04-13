package com.taylor.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamController {

    @GetMapping("/param")
    @SentinelResource(value = "param", blockHandler = "exHandler")
    public String param(String name) {
        return "ok";
    }

    public String exHandler(String name, BlockException e) {
        return "ParamController.exHandler error";
    }

}
