package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Instrument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InstrumentServiceTests {

    @Autowired
    private InstrumentService sut;

    @Test
    void testGetInstrumentFromName(){
        Instrument instrument = sut.getInstrument("piano");
        assertEquals("piano", instrument.getName().toLowerCase(Locale.ROOT));
        assertEquals("keyboard", instrument.getFamily());
        assertEquals("C4", instrument.getTransposition());
        assertEquals("C1", instrument.getLowNote());
        assertEquals("C8", instrument.getHighNote());
    }
}
