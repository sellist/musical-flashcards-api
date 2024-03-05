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
        assertEquals("G4",sut.midiToSharpNoteName(67));
        assertEquals("C5",sut.midiToSharpNoteName(72));
        assertEquals("C1",sut.midiToSharpNoteName(24));
        assertEquals("B0",sut.midiToSharpNoteName(23));
    }

    @Test
    public void testMidiToFlatNoteName() {
        assertEquals("C4",sut.midiToFlatNoteName(60));
        assertEquals("Bb3",sut.midiToFlatNoteName(58));
        assertEquals("G4",sut.midiToFlatNoteName(67));
        assertEquals("C5",sut.midiToFlatNoteName(72));
        assertEquals("C1",sut.midiToFlatNoteName(24));
        assertEquals("B0",sut.midiToFlatNoteName(23));
    }

    @Test
    public void testNoteNameToMidi() {
        assertEquals(60,sut.getMidiValue("C4"));
        assertEquals(58,sut.getMidiValue("Bb3"));
        assertEquals(67,sut.getMidiValue("G4"));
        assertEquals(72,sut.getMidiValue("C5"));
        assertEquals(24,sut.getMidiValue("C1"));
    }
}
