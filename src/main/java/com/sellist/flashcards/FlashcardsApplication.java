package com.sellist.flashcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FlashcardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
	}

}
