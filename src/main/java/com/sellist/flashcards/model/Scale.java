package com.sellist.flashcards.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Scale {

    @PostConstruct
    public void init() {
        System.out.println("MAJOR: " + MAJOR);
        System.out.println("MINOR: " + MINOR);
        System.out.println("PENTATONIC: " + PENTATONIC);
        System.out.println("BLUES: " + BLUES);
        System.out.println("WHOLE_TONE: " + WHOLE_TONE);
        System.out.println("CHROMATIC: " + CHROMATIC);
    }

    @Value("${scales.major}")
    public String MAJOR;

    @Value("${scales.minor}")
    public String MINOR;

    @Value("${scales.pentatonic}")
    public String PENTATONIC;

    @Value("${scales.blues}")
    public String BLUES;

    @Value("${scales.wholeTone}")
    public String WHOLE_TONE;

    @Value("${scales.chromatic}")
    public String CHROMATIC;

}
