package com.example.ServiceClassRoutine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController

public class ServiceClassRoutine {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClassRoutine.class, args);
	}
	@GetMapping("/service-one")
	public String serviceOne() {
		return "Hello from Service One!";
	}
}
