package com.vpbank.msa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Longpn3EchoApplication {
	
	@GetMapping("/{input}")
	public String echo(@PathVariable String input) {
		return "<html><body><h1>ahihi echo: " + input+"</h1></body></html>";
	}

	public static void main(String[] args) {
		SpringApplication.run(Longpn3EchoApplication.class, args);
	}

}
