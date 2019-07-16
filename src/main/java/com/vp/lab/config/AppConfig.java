package com.vp.lab.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("unused")
@Configuration
@ConfigurationProperties()
//@PropertySource("classpath:application.properties")
@Getter
@Setter
public class AppConfig {
	
	private String webhookVerifyToken;
}
