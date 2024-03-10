package com.sellist.flashcards.util;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return cacheProvider.intervalSizeToStepMap.get(difference);
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

    private Step getStep(String stepName) {
        return cacheProvider.stepNameToStepMap.get(stepName);
    }
}
