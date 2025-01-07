package com.sellist.flashcards.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoteServiceTest {

    private final NoteService sut;

    @Autowired
    NoteServiceTest(NoteService noteService) {
        this.sut = noteService;
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
    void testGenerateFlats() {
        assertEquals("Bb3",sut.generateNoteByMidiValue(58,-1).toString());
        assertEquals("Ab1",sut.generateNoteByMidiValue(32,-1).toString());
        assertEquals("Eb4",sut.generateNoteByMidiValue(63,-1).toString());
        assertEquals("Fb4",sut.generateNoteByMidiValue(64,-1).toString());
        assertEquals("Gb4",sut.generateNoteByMidiValue(66,-1).toString());
        assertEquals("Ab4",sut.generateNoteByMidiValue(68,-1).toString());
        assertEquals("Bb4",sut.generateNoteByMidiValue(70,-1).toString());

    }

    @Test
    void testGenerateDoubleFlats() {
        assertEquals("Cbb2",sut.generateNoteByMidiValue(34,-2).toString());
        assertEquals("Cbb4",sut.generateNoteByMidiValue(58,-2).toString());
        assertEquals("Dbb4",sut.generateNoteByMidiValue(60,-2).toString());
        assertEquals("Ebb4",sut.generateNoteByMidiValue(62,-2).toString());
        assertEquals("Fbb4",sut.generateNoteByMidiValue(63,-2).toString());
        assertEquals("Gbb4",sut.generateNoteByMidiValue(65,-2).toString());
        assertEquals("Abb4",sut.generateNoteByMidiValue(67,-2).toString());
        assertEquals("Bbb4",sut.generateNoteByMidiValue(69,-2).toString());
    }

    @Test
    void testGenerateDoubleFlatsFromName() {
        assertEquals("Cbb2",sut.generateNote("Cbb2").toString());
    }
}
