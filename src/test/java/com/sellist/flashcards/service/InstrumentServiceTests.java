package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Instrument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InstrumentServiceTests {

    private final InstrumentService instrumentService;

    public InstrumentServiceTests(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @Test
    void testGetInstrumentFromName(){
        Instrument exp = new Instrument();
        exp.setName("cello");
        exp.setFamily("strings");
        exp.setTransposition("C4");
    }
}
