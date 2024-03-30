package com.sellist.flashcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sellist.flashcards.*")
@EnableConfigurationProperties
public class FlashcardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
	}

}
