package com.taylor.controller;

import com.taylor.service.Payservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Autowired
    Payservice payservice;

    @GetMapping("/pay")
    public String pay () {
        payservice.save();
        return "success";
    }
}
