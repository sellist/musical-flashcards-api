package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.util.NoteUtil;
import com.sellist.flashcards.util.StepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ScaleService {

    @Autowired
    private NoteUtil noteUtil;

    @Autowired
    private StepUtil stepUtil;

    public List<Note> generateScale(String scalePattern, String startingNote, int numOctaves) {
        Note startNote = noteUtil.generateNote(startingNote);
        List<Note> scale = new ArrayList<>();
        List<Step> steps = stepUtil.getStepsFromPattern(scalePattern);

        Note lastNote = startNote;
        for (Step step : steps) {
            scale.add(lastNote);
            lastNote = stepUtil.stepUp(lastNote, step);
        }
        scale.add(lastNote);

        System.out.println("scale: " + scale);

        return scale;
    }
}
