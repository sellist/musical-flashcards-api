package com.sellist.flashcards.util;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StepUtil {

    @Autowired
    private NoteUtil noteUtil;

    @Autowired
    private CacheProvider cacheProvider;

    @Autowired
    private StepsConstants stepsConstants;

    public Step getDifference(Note note1, Note note2) {
        int midiValue1 = note1.getMidiValue();
        int midiValue2 = note2.getMidiValue();
        int difference = midiValue2 - midiValue1;
        return cacheProvider.stepCache.intervalSizeToStepMap.get(difference);
    }

    /**
     * Business logic for taking in a note and step, and returning the note after that step
     * @param note
     * @param step
     * @return
     */
    private Note handleStepDown(Note note, Step step) {
        String[] baseNotesReverse = {"B", "A", "G", "F", "E", "D", "C"};

        int inputNoteIndex = Arrays.asList(baseNotesReverse).indexOf(note.getNoteName().substring(0, 1));

        int targetNoteIndex;
        String targetNote;
        int targetOctave;

        targetNoteIndex = (inputNoteIndex + step.getDegree()) % baseNotesReverse.length;
        targetNote = baseNotesReverse[targetNoteIndex];
        targetOctave = noteUtil.getOctaveFromMidi(note.getMidiValue() - step.getSize());

        Note baseDegreeNote = noteUtil.generateNote(targetNote + targetOctave);


        int difference = (note.getMidiValue() - step.getSize()) - baseDegreeNote.getMidiValue();
        System.out.println("difference: " + difference);



        System.out.println("inputNote: " + note.getDebugString());
        System.out.println("baseDegreeNote: " + baseDegreeNote.getDebugString());

        Note t = noteUtil.generateNoteByMidiValue(note.getMidiValue() + step.getSize(), difference);

        System.out.println("new note:" + t);

        return t;
    }

    private Note handleStepUp(Note note, Step step) {
        String[] baseNotes = {"C", "D", "E", "F", "G", "A", "B"};

        int inputNoteIndex = Arrays.asList(baseNotes).indexOf(note.getNoteName().substring(0, 1));

        int targetNoteIndex = (inputNoteIndex + step.getDegree()) % baseNotes.length;
        String targetNote = baseNotes[targetNoteIndex];
        int targetOctave = noteUtil.getOctaveFromMidi(note.getMidiValue() + step.getSize());

        Note baseDegreeNote = noteUtil.generateNote(targetNote + targetOctave);


        int difference = (note.getMidiValue() + step.getSize()) - baseDegreeNote.getMidiValue();

        int octaveDiff = (targetOctave - note.getOctave()) * 12;

        // handles octave differences when calculating scale degree difference
        if (difference >= 10) {
            difference = difference - octaveDiff;
        } else if (difference <= -10) {
            difference = difference + octaveDiff;
        }

        return noteUtil.generateNoteByMidiValue(note.getMidiValue() + step.getSize(), difference);
    }

    public Note stepDown(Note note, String step) {
        return handleStepDown(note, getStep(step));
    }

    public Note stepDown(Note note, Step step) {
        return handleStepDown(note, step);
    }

    public Note stepUp(Note note, String step) {
        return handleStepUp(note, getStep(step));
    }

    public Note stepUp(Note note, Step step) {
        return handleStepUp(note, step);
    }

    public Step getStep(String stepName) {
        return cacheProvider.stepCache.stepNameToStepMap.get(stepName);
    }

    public List<Step> getStepsFromPattern(String pattern) {
        List<Step> steps = new ArrayList<>();
        for (String stepName : pattern.split(",")) {
            steps.add(getStep(stepName));
        }
        System.out.println(steps);
        return steps;
    }
}
