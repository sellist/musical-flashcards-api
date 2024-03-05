package com.sellist.flashcards.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MidiNoteUtilTest {
    @Autowired
    private MidiNoteUtil sut;

    @Test
    public void testMidiToSharpNoteName() {
        assertEquals("C4",sut.midiToSharpNoteName(60));
        assertEquals("B#4",sut.midiToSharpNoteName(60));
        assertEquals("G4",sut.midiToSharpNoteName(67));
        assertEquals("E#4",sut.midiToSharpNoteName(65));
        assertEquals("C5",sut.midiToSharpNoteName(72));
        assertEquals("C1",sut.midiToSharpNoteName(24));
    }

    @Test
    public void testMidiToFlatNoteName() {
        assertEquals("C4",sut.midiToFlatNoteName(60));
        assertEquals("Bb4",sut.midiToFlatNoteName(58));
        assertEquals("G4",sut.midiToFlatNoteName(67));
        assertEquals("C5",sut.midiToFlatNoteName(72));
        assertEquals("C1",sut.midiToFlatNoteName(24));
        assertEquals("Cb1",sut.midiToFlatNoteName(23));
    }
}
