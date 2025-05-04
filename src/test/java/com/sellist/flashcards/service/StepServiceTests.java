package com.sellist.flashcards.service;

import com.sellist.flashcards.constants.Steps;
import com.sellist.flashcards.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StepServiceTests {

    private final StepService stepService;

    private final NoteService noteService;

    @Autowired
    public StepServiceTests(StepService stepService, NoteService noteService, Steps steps) {
        this.stepService = stepService;
        this.noteService = noteService;
    }

    @Test
    void standardSteps() {
        Assertions.assertEquals(Steps.MINOR_SECOND,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("C#4")));
        Assertions.assertEquals(Steps.MAJOR_SECOND,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("D4")));
        Assertions.assertEquals(Steps.MINOR_THIRD,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Eb4")));
        Assertions.assertEquals(Steps.MAJOR_THIRD,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("E4")));
        Assertions.assertEquals(Steps.PERFECT_FOURTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("F4")));
        Assertions.assertEquals(Steps.TRITONE,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Gb4")));
        Assertions.assertEquals(Steps.PERFECT_FIFTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("G4")));
        Assertions.assertEquals(Steps.MINOR_SIXTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Ab4")));
        Assertions.assertEquals(Steps.MAJOR_SIXTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("A4")));
        Assertions.assertEquals(Steps.MINOR_SEVENTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("Bb4")));
        Assertions.assertEquals(Steps.MAJOR_SEVENTH,
                stepService.getDifference( noteService.generateNote("C4"),
                                        noteService.generateNote("B4")));
        Assertions.assertEquals(Steps.PERFECT_OCTAVE,
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

    @Test
    void testDoubleFlatStepUp() {
        Assertions.assertEquals(noteService.generateNote("Cb5"),
                stepService.stepUp(noteService.generateNote("Bbb4"), "W"));
    }

    @Test
    void testDoubleFlatStepDown() {
        Assertions.assertEquals(noteService.generateNote("Bbb4"),
                stepService.stepDown(noteService.generateNote("Cb5"), "W"));
    }

    @Test
    void testDoubleSharpStepUp() {
        Assertions.assertEquals(noteService.generateNote("D##4"),
                stepService.stepUp(noteService.generateNote("C##4"), "M2"));
        Assertions.assertEquals(noteService.generateNote("C##5"),
                stepService.stepUp(noteService.generateNote("B##4"), "H"));
        Assertions.assertEquals(noteService.generateNote("C##5"),
                stepService.stepUp(noteService.generateNote("B##4"), "m2"));
    }

    @Test
    void getNoteFromScalarDegreeHappy() {
        Note bFlat = noteService.generateNoteByMidiValue(71, 0);
        Assertions.assertEquals(bFlat, stepService.getNoteFromScalarDegree("C4","7"));

        Note a = noteService.generateNoteByMidiValue(69, 0);
        Assertions.assertEquals(a, stepService.getNoteFromScalarDegree("C4","6"));

        Note g = noteService.generateNoteByMidiValue(67, 0);
        Assertions.assertEquals(g, stepService.getNoteFromScalarDegree("C4","5"));

        Note f = noteService.generateNoteByMidiValue(65, 0);
        Assertions.assertEquals(f, stepService.getNoteFromScalarDegree("C4","4"));

        Note e = noteService.generateNoteByMidiValue(76, 0);
        Assertions.assertEquals(e, stepService.getNoteFromScalarDegree("A4","5"));

    }

    @Test
    void testGetNoteFromScalarDegreeFlat() {
        Assertions.assertEquals(noteService.generateNote("Ab4"),
                stepService.getNoteFromScalarDegree("C4", "b6"));
        Assertions.assertEquals(noteService.generateNote("Gb4"),
                stepService.getNoteFromScalarDegree("C4", "b5"));
        Assertions.assertEquals(noteService.generateNote("Db5"),
                stepService.getNoteFromScalarDegree("G4", "b5"));
        Assertions.assertEquals(noteService.generateNote("Eb5"),
                stepService.getNoteFromScalarDegree("G4", "b6"));
        Assertions.assertEquals(noteService.generateNote("Bb4"),
                stepService.getNoteFromScalarDegree("C4", "b7"));
    }

    @Test
    void testGetNoteFromScalarDegreeSharp() {
        Assertions.assertEquals(noteService.generateNote("A#4"),
                stepService.getNoteFromScalarDegree("C4", "#6"));
        Assertions.assertEquals(noteService.generateNote("F#4"),
                stepService.getNoteFromScalarDegree("C4", "#4"));
        Assertions.assertEquals(noteService.generateNote("C#5"),
                stepService.getNoteFromScalarDegree("G4", "#4"));
        Assertions.assertEquals(noteService.generateNote("E#5"),
                stepService.getNoteFromScalarDegree("G4", "#6"));
        Assertions.assertEquals(noteService.generateNote("G#4"),
                stepService.getNoteFromScalarDegree("C4", "#5"));
    }



}
