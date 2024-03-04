package com.sellist.flashcards.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Scale {

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
