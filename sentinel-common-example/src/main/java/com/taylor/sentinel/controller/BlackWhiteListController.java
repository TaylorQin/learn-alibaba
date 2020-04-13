package com.taylor.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.taylor.sentinel.config.DefaultBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackWhiteListController {

    @GetMapping("/black")
    public String black() {
        return "black";
    }

    @GetMapping("/white")
    public String white() {
        return "white";
    }

}
