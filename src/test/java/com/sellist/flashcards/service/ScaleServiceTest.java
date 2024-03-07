package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
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
        List<Note> expected = List.of(
                new Note("C4"),
                new Note("D4"),
                new Note("E4"),
                new Note("F4"),
                new Note("G4"),
                new Note("A4"),
                new Note("B4"),
                new Note("C5")
        );
        List<Note> data = sut.generateScale("WWHWWWH", "C4", 1);
        Assertions.assertEquals(expected, data);
    }

    @Test
    void testGetChromaticScale() {
        List<Note> expected = List.of(
                new Note("C4"),
                new Note("C#4"),
                new Note("D4"),
                new Note("D#4"),
                new Note("E4"),
                new Note("F4"),
                new Note("F#4"),
                new Note("G4"),
                new Note("G#4"),
                new Note("A4"),
                new Note("A#4"),
                new Note("B4"),
                new Note("C5")
        );
        List<Note> data = sut.generateScale("HHHHHHHHHHHH", "C4", 1);
        Assertions.assertEquals(expected, data);
    }

}
