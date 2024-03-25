package com.sellist.flashcards.util;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.src.CacheProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StepUtilTests {
    @Autowired
    private StepUtil stepUtil;

    @Autowired
    private NoteUtil noteUtil;

    @Autowired
    private StepsConstants stepsConstants;

    @Autowired
    private CacheProvider cacheProvider;

    @Test
    void standardSteps() {
        Assertions.assertEquals(stepsConstants.MINOR_SECOND,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("C#4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SECOND,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("D4")));
        Assertions.assertEquals(stepsConstants.MINOR_THIRD,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("Eb4")));
        Assertions.assertEquals(stepsConstants.MAJOR_THIRD,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("E4")));
        Assertions.assertEquals(stepsConstants.PERFECT_FOURTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("F4")));
        Assertions.assertEquals(stepsConstants.TRITONE,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("Gb4")));
        Assertions.assertEquals(stepsConstants.PERFECT_FIFTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("G4")));
        Assertions.assertEquals(stepsConstants.MINOR_SIXTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("Ab4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SIXTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("A4")));
        Assertions.assertEquals(stepsConstants.MINOR_SEVENTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("Bb4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SEVENTH,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("B4")));
        Assertions.assertEquals(stepsConstants.PERFECT_OCTAVE,
                stepUtil.getDifference( noteUtil.generateNote("C4"),
                                        noteUtil.generateNote("C5")));
    }

    @Test
    void naturalNoteByStepUp() {
        Assertions.assertEquals(noteUtil.generateNote("D4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "M2"));
        Assertions.assertEquals(noteUtil.generateNote("E4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "M3"));
        Assertions.assertEquals(noteUtil.generateNote("F4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "P4"));
        Assertions.assertEquals(noteUtil.generateNote("G4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "P5"));
        Assertions.assertEquals(noteUtil.generateNote("A4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "M6"));
        Assertions.assertEquals(noteUtil.generateNote("B4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "M7"));
        Assertions.assertEquals(noteUtil.generateNote("C5"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "P8"));
    }

    @Test
    void minorNoteByStepUp() {
        Assertions.assertEquals(noteUtil.generateNote("Db4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "m2"));
        Assertions.assertEquals(noteUtil.generateNote("Eb4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "m3"));
        Assertions.assertEquals(noteUtil.generateNote("F4"),
                stepUtil.stepUp(noteUtil.generateNote("D4"), "m3"));
    }

    @Test
    void naturalByStepDown() {
        Assertions.assertEquals(noteUtil.generateNote("B3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "m2"));
        Assertions.assertEquals(noteUtil.generateNote("A3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "m3"));
        Assertions.assertEquals(noteUtil.generateNote("G3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "P4"));
        Assertions.assertEquals(noteUtil.generateNote("F3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "P5"));
        Assertions.assertEquals(noteUtil.generateNote("E3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "m6"));
        Assertions.assertEquals(noteUtil.generateNote("D3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "m7"));
        Assertions.assertEquals(noteUtil.generateNote("C3"),
                stepUtil.stepDown(noteUtil.generateNote("C4"), "P8"));
    }
    @Test
    void sharpNoteByStepUp() {
        Assertions.assertEquals(noteUtil.generateNote("D#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "M2"));
        Assertions.assertEquals("D#4",
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "M2").toString());
        Assertions.assertEquals(noteUtil.generateNote("E#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "M3"));
        Assertions.assertEquals(noteUtil.generateNote("F#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "P4"));
        Assertions.assertEquals(noteUtil.generateNote("G#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "P5"));
        Assertions.assertEquals(noteUtil.generateNote("A#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "M6"));
        Assertions.assertEquals(noteUtil.generateNote("B#4"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "M7"));
        Assertions.assertEquals(noteUtil.generateNote("C#5"),
                stepUtil.stepUp(noteUtil.generateNote("C#4"), "P8"));
    }

    @Test
    void getSharpFromNatural() {
        Assertions.assertEquals(noteUtil.generateNote("C#4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "A1"));
        Assertions.assertEquals(noteUtil.generateNote("D#4"),
                stepUtil.stepUp(noteUtil.generateNote("C4"), "A2"));
        Assertions.assertEquals(noteUtil.generateNote("F#4"),
                stepUtil.stepUp(noteUtil.generateNote("D4"), "M3"));
        Assertions.assertEquals(noteUtil.generateNote("F#5"),
                stepUtil.stepUp(noteUtil.generateNote("G4"), "M7"));
        Assertions.assertEquals(noteUtil.generateNote("F5"),
                stepUtil.stepUp(noteUtil.generateNote("G4"), "m7"));

    }
}
