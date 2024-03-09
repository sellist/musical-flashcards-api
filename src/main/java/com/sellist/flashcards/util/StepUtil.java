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

    public Note getNoteByStep(Note note, String stepName) {
        Integer size = cacheProvider.intervalNameToSizeMap.get(stepName);
        if (stepName.startsWith("M") || stepName.startsWith("P")) {
            Step step = cacheProvider.majorSizeToStepMap.get(size);
        } else if (stepName.startsWith("m")) {
            Step step = cacheProvider.minorSizeToStepMap.get(size);
        } else if  (stepName.startsWith("A")) {
            Step step = cacheProvider.intervalSizeToStepMap.get(size);
        }

        // todo
        return new Note(note.getMidiValue() + size, noteUtil.midiToSharpNoteName(note.getMidiValue() + size));

    }
}
