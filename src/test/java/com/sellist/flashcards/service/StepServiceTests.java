package com.sellist.flashcards.service;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.service.NoteService;
import com.sellist.flashcards.service.StepService;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StepServiceTests {
    @Autowired
    private StepService stepService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private StepsConstants stepsConstants;

    @Autowired
    private MemoryCacheProvider cacheProvider;

    @Test
    void standardSteps() {
        Assertions.assertEquals(stepsConstants.MINOR_SECOND,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("C#4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SECOND,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("D4")));
        Assertions.assertEquals(stepsConstants.MINOR_THIRD,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Eb4")));
        Assertions.assertEquals(stepsConstants.MAJOR_THIRD,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("E4")));
        Assertions.assertEquals(stepsConstants.PERFECT_FOURTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("F4")));
        Assertions.assertEquals(stepsConstants.TRITONE,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Gb4")));
        Assertions.assertEquals(stepsConstants.PERFECT_FIFTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("G4")));
        Assertions.assertEquals(stepsConstants.MINOR_SIXTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Ab4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SIXTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("A4")));
        Assertions.assertEquals(stepsConstants.MINOR_SEVENTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Bb4")));
        Assertions.assertEquals(stepsConstants.MAJOR_SEVENTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("B4")));
        Assertions.assertEquals(stepsConstants.PERFECT_OCTAVE,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("C5")));
    }

    @Test
    void naturalNoteByStepUp() {
        Assertions.assertEquals(noteService.generateNote("D4"),
                stepService.stepUp(noteService.generateNote("C4"), "M2"));
        Assertions.assertEquals(noteService.generateNote("E4"),
                stepService.stepUp(noteService.generateNote("C4"), "M3"));
        Assertions.assertEquals(noteService.generateNote("F4"),
                stepService.stepUp(noteService.generateNote("C4"), "P4"));
        Assertions.assertEquals(noteService.generateNote("G4"),
                stepService.stepUp(noteService.generateNote("C4"), "P5"));
        Assertions.assertEquals(noteService.generateNote("A4"),
                stepService.stepUp(noteService.generateNote("C4"), "M6"));
        Assertions.assertEquals(noteService.generateNote("B4"),
                stepService.stepUp(noteService.generateNote("C4"), "M7"));
        Assertions.assertEquals(noteService.generateNote("C5"),
                stepService.stepUp(noteService.generateNote("C4"), "P8"));
    }

    @Test
    void minorNoteByStepUp() {
        Assertions.assertEquals(noteService.generateNote("Db4"),
                stepService.stepUp(noteService.generateNote("C4"), "m2"));
        Assertions.assertEquals(noteService.generateNote("Eb4"),
                stepService.stepUp(noteService.generateNote("C4"), "m3"));
        Assertions.assertEquals(noteService.generateNote("F4"),
                stepService.stepUp(noteService.generateNote("D4"), "m3"));
    }

    @Test
    void naturalByStepDown() {
        Assertions.assertEquals(noteService.generateNote("B3"),
                stepService.stepDown(noteService.generateNote("C4"), "m2"));
        Assertions.assertEquals(noteService.generateNote("A3"),
                stepService.stepDown(noteService.generateNote("C4"), "m3"));
        Assertions.assertEquals(noteService.generateNote("G3"),
                stepService.stepDown(noteService.generateNote("C4"), "P4"));
        Assertions.assertEquals(noteService.generateNote("F3"),
                stepService.stepDown(noteService.generateNote("C4"), "P5"));
        Assertions.assertEquals(noteService.generateNote("E3"),
                stepService.stepDown(noteService.generateNote("C4"), "m6"));
        Assertions.assertEquals(noteService.generateNote("D3"),
                stepService.stepDown(noteService.generateNote("C4"), "m7"));
        Assertions.assertEquals(noteService.generateNote("C3"),
                stepService.stepDown(noteService.generateNote("C4"), "P8"));
    }

    @Test
    void sharpNoteByStepDown() {
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("D#4"), "M2"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("E#4"), "M3"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("F#4"), "P4"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("G#4"), "P5"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("A#4"), "M6"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("B#4"), "M7"));
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepDown(noteService.generateNote("C#5"), "P8"));
    }

    @Test
    void minorNoteByStepDown() {
        Assertions.assertEquals(noteService.generateNote("C4"),
                stepService.stepDown(noteService.generateNote("Db4"), "m2"));
        Assertions.assertEquals(noteService.generateNote("C4"),
                stepService.stepDown(noteService.generateNote("Eb4"), "m3"));
        Assertions.assertEquals(noteService.generateNote("D4"),
                stepService.stepDown(noteService.generateNote("F4"), "m3"));
    }

    @Test
    void sharpNoteByStepUp() {
        Assertions.assertEquals(noteService.generateNote("D#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "M2"));
        Assertions.assertEquals("D#4",
                stepService.stepUp(noteService.generateNote("C#4"), "M2").toString());
        Assertions.assertEquals(noteService.generateNote("E#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "M3"));
        Assertions.assertEquals(noteService.generateNote("F#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "P4"));
        Assertions.assertEquals(noteService.generateNote("G#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "P5"));
        Assertions.assertEquals(noteService.generateNote("A#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "M6"));
        Assertions.assertEquals(noteService.generateNote("B#4"),
                stepService.stepUp(noteService.generateNote("C#4"), "M7"));
        Assertions.assertEquals(noteService.generateNote("C#5"),
                stepService.stepUp(noteService.generateNote("C#4"), "P8"));
    }

    @Test
    void getSharpFromNatural() {
        Assertions.assertEquals(noteService.generateNote("C#4"),
                stepService.stepUp(noteService.generateNote("C4"), "A1"));
        Assertions.assertEquals(noteService.generateNote("D#4"),
                stepService.stepUp(noteService.generateNote("C4"), "A2"));
        Assertions.assertEquals(noteService.generateNote("F#4"),
                stepService.stepUp(noteService.generateNote("D4"), "M3"));
        Assertions.assertEquals(noteService.generateNote("F#5"),
                stepService.stepUp(noteService.generateNote("G4"), "M7"));
        Assertions.assertEquals(noteService.generateNote("F5"),
                stepService.stepUp(noteService.generateNote("G4"), "m7"));

    }
}
