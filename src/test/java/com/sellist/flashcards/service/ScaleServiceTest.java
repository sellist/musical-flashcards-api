package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Scale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScaleServiceTest {

    private final ScaleService sut;

    @Autowired
    public ScaleServiceTest(ScaleService scaleService) {
        this.sut = scaleService;
    }

    @Test
    void generateCMajScale() {
        Scale scale = sut.generateScale("W,W,H,W,W,W,H", "C4", 1);
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
        Scale scale = sut.generateScale("W,W,H,W,W,W,H", "D4", 1);
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
        Scale scale = sut.generateScale("W,W,H,W,W,W,H", "E4", 1);
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
        Scale scale = sut.generateScale("W,W,H,W,W,W,H", "F4", 1);
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
        Scale scale = sut.generateScale("W,W,H,W,W,W,H", "Bb4", 1);
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

    @Test
    void generateSequentialRangeBetweenNotesNinth() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","C4", "D5");
        Assertions.assertEquals(9, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("D5", scale.get(scale.size()-1).toString());
    }

    @Test
    void generateLargeRangeBetweenNotes() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","C4", "C8");
        Assertions.assertEquals(29, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("D5", scale.get(8).toString());
        Assertions.assertEquals("C8", scale.get(scale.size()-1).toString());
    }

    @Test
    void generateRangeBetweenTwoNonScalarNotes() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","C4", "D#5");
        Assertions.assertEquals(10, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("D#5", scale.get(scale.size()-1).toString());
    }

    @Disabled("Disabled until range function is added")
    @Test
    void generateRangeChromatic() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("sharp_chromatic","C4", "C5");
        Assertions.assertEquals(13, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("C5", scale.get(scale.size()-1).toString());
    }

    @Test
    void genRangeSharps() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","C#4", "D#5");
        Assertions.assertEquals(9, scale.size());
        Assertions.assertEquals("C#4", scale.get(0).toString());
        Assertions.assertEquals("D#5", scale.get(scale.size()-1).toString());
    }

    @Test
    void genRangeFlats() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","Db4", "Eb5");
        Assertions.assertEquals(9, scale.size());
        Assertions.assertEquals("Db4", scale.get(0).toString());
        Assertions.assertEquals("Eb5", scale.get(scale.size()-1).toString());
    }

    @Test
    void genRangeFlatsEdgeCase() {
        List<Note> scale = sut.generateSequentialRangeBetweenNotes("major","Fb3", "Cb5");
        Assertions.assertEquals(12, scale.size());
        Assertions.assertEquals("Fb3", scale.get(0).toString());
        Assertions.assertEquals("Gb3", scale.get(1).toString());
        Assertions.assertEquals("Ab3", scale.get(2).toString());
        Assertions.assertEquals("Bbb3", scale.get(3).toString());
        Assertions.assertEquals("Cb4", scale.get(4).toString());
        Assertions.assertEquals("Db4", scale.get(5).toString());
        Assertions.assertEquals("Cb5", scale.get(scale.size()-1).toString());
    }

    @Test
    void testScalesFromDegrees() {
        List<Note> scale = sut.buildScale("sharp_chromatic", "C4", 1);
        System.out.println(scale);

        Assertions.assertEquals(13, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("C#4", scale.get(1).toString());
        Assertions.assertEquals("D4", scale.get(2).toString());
        Assertions.assertEquals("D#4", scale.get(3).toString());
        Assertions.assertEquals("E4", scale.get(4).toString());
        Assertions.assertEquals("F4", scale.get(5).toString());
        Assertions.assertEquals("F#4", scale.get(6).toString());
        Assertions.assertEquals("G4", scale.get(7).toString());
        Assertions.assertEquals("G#4", scale.get(8).toString());
        Assertions.assertEquals("A4", scale.get(9).toString());
        Assertions.assertEquals("A#4", scale.get(10).toString());
        Assertions.assertEquals("B4", scale.get(11).toString());
        Assertions.assertEquals("C5", scale.get(12).toString());
    }

}
