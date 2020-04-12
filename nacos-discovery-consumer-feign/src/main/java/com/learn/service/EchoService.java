package com.learn.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "nacos-discovery-provider")
public interface EchoService {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String param);

//    @GetMapping("/echo/{str}") //使用包含path variable的接口的时候，不能使用GetMapping
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    String echoPathvariable(@PathVariable("str") String param);

}
