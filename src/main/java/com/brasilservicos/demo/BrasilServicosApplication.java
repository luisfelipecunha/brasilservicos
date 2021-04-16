package com.brasilservicos.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BrasilServicosApplication {

	public static void main(String[] args) {
		log.warn("Subiu 2");
		SpringApplication.run(BrasilServicosApplication.class, args);
	}
}
