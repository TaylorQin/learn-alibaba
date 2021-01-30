package com.taylor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RestController
	public static class EchoController {

		@Value("${spring.application.name}")
		private String appName;

		@Autowired
		private LoadBalancerClient loadBalancerClient;
		@Autowired
		private RestTemplate restTemplate;

		@GetMapping("/echo")
		String echo(@RequestParam("name") String str) {
	//        ServiceInstance instance = loadBalancerClient.choose("nacos-discovery-provider");
	//        String url = String.format("http://%s:%s/echo/%s",instance.getHost(),instance.getPort(),appName+" "+str);
			return restTemplate.getForObject(String.format("http://nacos-discovery-provider/echo?name=%s",appName + " "+str), String.class);
		}

		@GetMapping("/echo/{str}")
		String echoPathVariable(@PathVariable("str") String str) {
	//        ServiceInstance instance = loadBalancerClient.choose("nacos-discovery-provider");
	//        String url = String.format("http://%s:%s/echo/%s",instance.getHost(),instance.getPort(),appName+" "+str);
			return restTemplate.getForObject(String.format("http://nacos-discovery-provider/echo/%s",appName + " "+str), String.class);
		}

	}
}
