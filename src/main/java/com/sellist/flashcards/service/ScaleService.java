package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScaleService implements ProvideApiInfo {

    @Autowired
    private NoteService noteService;

    @Autowired
    private StepService stepService;

    @Autowired
    MemoryCacheProvider memoryCacheProvider;

    public List<Note> generateScale(String scalePattern, String startingNote, int numOctaves) {
        Note startNote = noteService.generateNote(startingNote);
        List<Note> scale = new ArrayList<>();

        List<String> scalePatternList = List.of(scalePattern.split(","));
        List<String> modifiedScalePattern = new ArrayList<>();
        for (int i = 0; i < numOctaves; i++) {
            modifiedScalePattern.addAll(scalePatternList);
        }

        List<Step> steps = stepService.getStepsFromPattern(modifiedScalePattern);
        System.out.println(modifiedScalePattern);

        System.out.println(steps);
        Note lastNote = startNote;

        for (Step step : steps) {
            scale.add(lastNote);
            lastNote = stepService.stepUp(lastNote, step);
        }
        scale.add(lastNote);
        return scale;
    }

    public String getScalePattern(String scaleName) {
        return memoryCacheProvider.noteCache.scaleNameToPattern.get(scaleName.toLowerCase());
    }

    @Override
    public List<String> listAvailable() {
        return memoryCacheProvider.noteCache.scaleNameToPattern.keySet().stream().toList();
    }


}
