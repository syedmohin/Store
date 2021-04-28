package com.afc.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			
		};

	}

}
