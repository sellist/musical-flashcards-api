package com.sellist.flashcards;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = "com.sellist.flashcards.*")
@EnableConfigurationProperties
@EnableAsync
@Log4j2
public class FlashcardsApplication {
	public static void main(String[] args) {
		log.info("Swagger: localhost:8080/swagger-ui.html");
		SpringApplication.run(FlashcardsApplication.class, args);
	}

}
