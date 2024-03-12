package com.sellist.flashcards.util;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepUtil {

    //    Empty string offsets indexes to match scale degrees
    private String[] baseNotes = {"","C", "D", "E", "F", "G", "A", "B"};

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

    public Note getNoteByStepUp(Note note, String stepName) {
        Step step = getStep(stepName);
        int newMidiValue = note.getMidiValue() + step.getSize();
        if (note.getNoteName().contains("b")) {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "b");
        } else if (note.getNoteName().contains("#")) {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "#");
        } else {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "");
        }
    }

    public Note getNoteByStepDown(Note note, String stepName) {
        Step step = getStep(stepName);
        int newMidiValue = note.getMidiValue() - step.getSize();
        if (note.getNoteName().contains("b")) {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "b");
        } else if (note.getNoteName().contains("#")) {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "#");
        } else {
            return noteUtil.generateNoteByMidiValue(newMidiValue, "");
        }
    }

    /**
     * Business logic for taking in a note and step, and returning the note after that step
     * @param note
     * @param step
     * @return
     */
    private Note handleStep(Note note, Step step, boolean down) {
//        todo
        return null;
    }

    private Note handleStep(Note note, Step step) {
        return handleStep(note, step, false);
    }

    private Step getStep(String stepName) {
        return cacheProvider.stepCache.stepNameToStepMap.get(stepName);
    }
}
