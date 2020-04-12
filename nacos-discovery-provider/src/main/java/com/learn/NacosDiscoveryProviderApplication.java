package com.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryProviderApplication.class, args);
	}

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
}
