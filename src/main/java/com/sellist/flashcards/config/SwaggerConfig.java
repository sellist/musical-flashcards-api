package com.sellist.flashcards.config;

import lombok.extern.log4j.Log4j2;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("controller-api")
                .packagesToScan("com.sellist.flashcards.controller") // Specify the package to scan
                .build();
    }
}
