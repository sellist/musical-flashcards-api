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
    void generateCMajScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "C4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("D4", scale.get(1).toString());
        Assertions.assertEquals("E4", scale.get(2).toString());
        Assertions.assertEquals("F4", scale.get(3).toString());
        Assertions.assertEquals("G4", scale.get(4).toString());
        Assertions.assertEquals("A4", scale.get(5).toString());
        Assertions.assertEquals("B4", scale.get(6).toString());
        Assertions.assertEquals("C5", scale.get(7).toString());
    }

    @Test
    void generateDMajScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "D4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("D4", scale.get(0).toString());
        Assertions.assertEquals("E4", scale.get(1).toString());
        Assertions.assertEquals("F#4", scale.get(2).toString());
        Assertions.assertEquals("G4", scale.get(3).toString());
        Assertions.assertEquals("A4", scale.get(4).toString());
        Assertions.assertEquals("B4", scale.get(5).toString());
        Assertions.assertEquals("C#5", scale.get(6).toString());
        Assertions.assertEquals("D5", scale.get(7).toString());
    }

    @Test
    void generateEMajScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "E4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("E4", scale.get(0).toString());
        Assertions.assertEquals("F#4", scale.get(1).toString());
        Assertions.assertEquals("G#4", scale.get(2).toString());
        Assertions.assertEquals("A4", scale.get(3).toString());
        Assertions.assertEquals("B4", scale.get(4).toString());
        Assertions.assertEquals("C#5", scale.get(5).toString());
        Assertions.assertEquals("D#5", scale.get(6).toString());
        Assertions.assertEquals("E5", scale.get(7).toString());
    }

    @Test
    void generateFMajScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "F4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("F4", scale.get(0).toString());
        Assertions.assertEquals("G4", scale.get(1).toString());
        Assertions.assertEquals("A4", scale.get(2).toString());
        Assertions.assertEquals("Bb4", scale.get(3).toString());
        Assertions.assertEquals("C5", scale.get(4).toString());
        Assertions.assertEquals("D5", scale.get(5).toString());
        Assertions.assertEquals("E5", scale.get(6).toString());
        Assertions.assertEquals("F5", scale.get(7).toString());
    }

    @Test
    void generateBbMajScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "Bb4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("Bb4", scale.get(0).toString());
        Assertions.assertEquals("C5", scale.get(1).toString());
        Assertions.assertEquals("D5", scale.get(2).toString());
        Assertions.assertEquals("Eb5", scale.get(3).toString());
        Assertions.assertEquals("F5", scale.get(4).toString());
        Assertions.assertEquals("G5", scale.get(5).toString());
        Assertions.assertEquals("A5", scale.get(6).toString());
        Assertions.assertEquals("Bb5", scale.get(7).toString());
    }

}
