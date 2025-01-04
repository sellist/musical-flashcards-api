package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.ScaleOptions;
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

    public Scale generateScale(String scalePattern, String startingNote, int numOctaves) {
        Note startNote = noteService.generateNote(startingNote);
        ScaleOptions scaleOptions = new ScaleOptions(scalePattern, startNote, numOctaves);
        List<Note> scale = new ArrayList<>();

        List<String> scalePatternList = List.of(scalePattern.split(","));
        List<String> modifiedScalePattern = new ArrayList<>();
        for (int i = 0; i < numOctaves; i++) {
            modifiedScalePattern.addAll(scalePatternList);
        }

        List<Step> steps = stepService.getStepsFromPattern(modifiedScalePattern);

        Note lastNote = startNote;

        for (Step step : steps) {
            scale.add(lastNote);
            lastNote = stepService.stepUp(lastNote, step);
        }
        scale.add(lastNote);
        return new Scale(scale, scaleOptions);
    }

    public List<Note> generateSequentialRangeBetweenNotes(String scaleType, String startingNote, String endingNote) {
        Note startNote = noteService.generateNote(startingNote);
        Note endNote = noteService.generateNote(endingNote);

        List<Note> scale = new ArrayList<>();

        List<Step> steps = stepService.getStepsFromPattern(getScalePattern(scaleType));
        Note prevNote = startNote;

        int stepCounter = 0;
        while (prevNote.getMidiValue() < endNote.getMidiValue()) {
            scale.add(prevNote);
            prevNote = stepService.stepUp(prevNote, steps.get(stepCounter));
            stepCounter++;
            if (stepCounter >= steps.size()) {
                stepCounter = 0;
            }
        }
        if (prevNote.getMidiValue() == endNote.getMidiValue()) {
            scale.add(prevNote);
        } else {
            scale.add(endNote);
        }

        return scale;
    }

    public Scale generateNonSequentialScale(String scalePattern, String startingNote) {
        Note startNote = noteService.generateNote(startingNote);
        ScaleOptions scaleOptions = new ScaleOptions(scalePattern, startNote, 1);
        List<Note> scale = new ArrayList<>();

        List<Step> steps = stepService.getStepsFromPattern(List.of(scalePattern.split(",")));
        Note lastNote = startNote;

        for (Step step : steps) {
            scale.add(lastNote);
            lastNote = stepService.stepUp(lastNote, step);
        }
        scale.add(lastNote);
        return new Scale(scale, scaleOptions);
    }

    public String getScalePattern(String scaleName) {
        return memoryCacheProvider.noteCache.scaleNameToPattern.get(scaleName.toLowerCase());
    }

    @Override
    public List<String> listAvailable() {
        return memoryCacheProvider.noteCache.scaleNameToPattern.keySet().stream().toList();
    }
}