package com.sellist.flashcards.service;

import com.sellist.flashcards.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoteServiceTest {
    @Autowired
    private NoteService sut;

    @Test
    void testMidiToSharpNoteName() {
        assertEquals("C#4",sut.midiToSharpNoteName(61));
        assertEquals("D#4",sut.midiToSharpNoteName(63));
        assertEquals("E#4",sut.midiToSharpNoteName(65));
        assertEquals("F#4",sut.midiToSharpNoteName(66));
        assertEquals("G#4",sut.midiToSharpNoteName(68));
        assertEquals("A#4",sut.midiToSharpNoteName(70));
        assertEquals("B#4",sut.midiToSharpNoteName(72));
        assertEquals("C#5",sut.midiToSharpNoteName(73));
    }

    @Test
    void testMidiToFlatNoteName() {
        assertEquals("Ab3",sut.midiToFlatNoteName(56));
        assertEquals("Bb3",sut.midiToFlatNoteName(58));
        assertEquals("Cb4",sut.midiToFlatNoteName(59));
        assertEquals("Db4",sut.midiToFlatNoteName(61));
        assertEquals("Eb4",sut.midiToFlatNoteName(63));
        assertEquals("Fb4",sut.midiToFlatNoteName(64));
        assertEquals("Gb4",sut.midiToFlatNoteName(66));
        assertEquals("Ab4",sut.midiToFlatNoteName(68));
        assertEquals("Bb4",sut.midiToFlatNoteName(70));
    }

    @Test
    void testNoteNameToMidi() {
        assertEquals(60,sut.getMidiValue("C4"));
        assertEquals(58,sut.getMidiValue("Bb3"));
        assertEquals(68,sut.getMidiValue("G#4"));
        assertEquals(72,sut.getMidiValue("C5"));
        assertEquals(24,sut.getMidiValue("C1"));
    }

    @Test
    void testFlatNoteNameToMidi() {
        assertEquals(56,sut.getMidiValue("Ab3"));
        assertEquals(58,sut.getMidiValue("Bb3"));
        assertEquals(59,sut.getMidiValue("Cb4"));
        assertEquals(61,sut.getMidiValue("Db4"));
        assertEquals(63,sut.getMidiValue("Eb4"));
        assertEquals(64,sut.getMidiValue("Fb4"));
        assertEquals(66,sut.getMidiValue("Gb4"));
        assertEquals(68,sut.getMidiValue("Ab4"));
        assertEquals(70,sut.getMidiValue("Bb4"));
    }

    @Test
    void testSharpNoteNameToMidi() {
        assertEquals(61,sut.getMidiValue("C#4"));
        assertEquals(63,sut.getMidiValue("D#4"));
        assertEquals(65,sut.getMidiValue("E#4"));
        assertEquals(66,sut.getMidiValue("F#4"));
        assertEquals(68,sut.getMidiValue("G#4"));
        assertEquals(70,sut.getMidiValue("A#4"));
        assertEquals(72,sut.getMidiValue("B#4"));
        assertEquals(73,sut.getMidiValue("C#5"));
    }

    @Test
    void testNaturalNoteNameToMidi() {
        assertEquals(60,sut.getMidiValue("C4"));
        assertEquals(62,sut.getMidiValue("D4"));
        assertEquals(64,sut.getMidiValue("E4"));
        assertEquals(65,sut.getMidiValue("F4"));
        assertEquals(67,sut.getMidiValue("G4"));
        assertEquals(69,sut.getMidiValue("A4"));
        assertEquals(71,sut.getMidiValue("B4"));
        assertEquals(72,sut.getMidiValue("C5"));
    }

    @Test
    void testGetScalePattern() {
        assertEquals("W,W,H,W,W,W,H",sut.scaleNameToPattern("major"));
        assertEquals("W,H,W,W,H,A,H",sut.scaleNameToPattern("harmonic_minor"));
    }

    @Test
    void testGenerateFlats() {
        assertEquals("Bb3",sut.generateNoteByMidiValue(58,-1).toString());

    }

    @Test
    void testGenerateDoubleFlats() {
        assertEquals("Bbb3",sut.generateNoteByMidiValue(57,-2).toString());
        assertEquals("Cbb4",sut.generateNoteByMidiValue(58,-2).toString());
        assertEquals("Dbb4",sut.generateNoteByMidiValue(60,-2).toString());
        assertEquals("Ebb4",sut.generateNoteByMidiValue(62,-2).toString());
        assertEquals("Fbb4",sut.generateNoteByMidiValue(63,-2).toString());
    }
}
