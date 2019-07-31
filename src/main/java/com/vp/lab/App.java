package com.vp.lab;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class App {

	public static void main(String[] args) {
		log.fatal("FATAL log enabled.");
		log.error("ERROR log enabled.");
		log.warn("WARN log enabled.");
		log.info("INFO log enabled.");
		log.debug("DEBUG log enabled.");
		log.trace("TRACE log enabled.");
		org.springframework.boot.SpringApplication.run(App.class, args);
	}

}
