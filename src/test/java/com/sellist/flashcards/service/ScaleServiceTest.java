package com.sellist.flashcards.service;

import com.sellist.flashcards.model.MidiNote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScaleServiceTest {

    @Autowired
    private ScaleService sut;

    @Test
    void testGetScale() {
        List<MidiNote> expected = List.of(
                new MidiNote("C4"),
                new MidiNote("D4"),
                new MidiNote("E4"),
                new MidiNote("F4"),
                new MidiNote("G4"),
                new MidiNote("A4"),
                new MidiNote("B4"),
                new MidiNote("C5")
        );
        List<MidiNote> data = sut.generateScale("WWHWWWH", "C4", 1);
        Assertions.assertEquals(expected, data);
    }

    @Test
    void testGetChromaticScale() {
        List<MidiNote> expected = List.of(
                new MidiNote("C4"),
                new MidiNote("C#4"),
                new MidiNote("D4"),
                new MidiNote("D#4"),
                new MidiNote("E4"),
                new MidiNote("F4"),
                new MidiNote("F#4"),
                new MidiNote("G4"),
                new MidiNote("G#4"),
                new MidiNote("A4"),
                new MidiNote("A#4"),
                new MidiNote("B4"),
                new MidiNote("C5")
        );
        List<MidiNote> data = sut.generateScale("HHHHHHHHHHHH", "C4", 1);
        Assertions.assertEquals(expected, data);
    }

}
