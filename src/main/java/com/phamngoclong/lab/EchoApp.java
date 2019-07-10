package com.phamngoclong.lab;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.phamngoclong.lab.model.Message;

@SpringBootApplication
@RestController
public class EchoApp {
	
	@GetMapping("/echo/{input}")
	public ResponseEntity<Message> echo(@PathVariable String input) {
		
		Message mes = new Message();
		mes.setContent(input);
		mes.setTime(new Date());
		
		return ResponseEntity.ok(mes);
	}

	public static void main(String[] args) {
		SpringApplication.run(EchoApp.class, args);
	}

}
