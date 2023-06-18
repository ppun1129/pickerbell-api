package com.pickerbell.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PickerbellApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickerbellApiApplication.class, args);
	}

}
