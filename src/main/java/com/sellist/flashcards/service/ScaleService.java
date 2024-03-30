package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScaleService {

    @Autowired
    private NoteService noteService;

    @Autowired
    private StepService stepService;

    public List<Note> generateScale(String scalePattern, String startingNote, int numOctaves) {
        Note startNote = noteService.generateNote(startingNote);
        List<Note> scale = new ArrayList<>();
        List<Step> steps = stepService.getStepsFromPattern(scalePattern);

        Note lastNote = startNote;
        for (Step step : steps) {
            scale.add(lastNote);
            lastNote = stepService.stepUp(lastNote, step);
        }
        scale.add(lastNote);
        return scale;
    }
}
