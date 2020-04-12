package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class EchoController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/echo/{name}")
    Mono<String> echo(@PathVariable("name") String str) {
        return webClientBuilder.build()
                .get()
                .uri(String.format("http://nacos-discovery-provider/echo?name=%s", appName + " " + str))
                .retrieve()
                .bodyToMono(String.class);
    }

}
