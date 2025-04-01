package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.MusiCache;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.ScaleOptions;
import com.sellist.flashcards.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScaleService implements ProvideApiInfo {

    private final NoteService noteService;
    private final StepService stepService;
    private final MusiCache musiCache;

    @Autowired
    public ScaleService(NoteService noteService, StepService stepService, MusiCache musiCache) {
        this.noteService = noteService;
        this.stepService = stepService;
        this.musiCache = musiCache;
    }

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

    public List<Note> buildScale(String pattern, String startingNote, int numOctaves) {
        Note startNote = noteService.generateNote(startingNote);
        List<Step> steps = musiCache.scaleNameToScaleDegrees(pattern);
        Note currentNote = startNote;
        List<Note> scale = new ArrayList<>();

        // TODO - implement this method
        // from scale name, string/Note starting note, and number of octaves, generate a scale
        // then extrapolate that logic into a new range method that takes a start and end note

        // TODO
        // reason this is failing is `steps` variable generated from musicache has nulls in it
        for (Step step : steps) {
            currentNote = stepService.stepUp(currentNote, step);
            scale.add(currentNote);
        }

        return scale;
    }

    public List<Note> generateSequentialRangeBetweenNotes(String scaleType, String startingNote, String endingNote) {
        Note startNote = noteService.generateNote(startingNote);
        Note endNote = noteService.generateNote(endingNote);

        List<Note> scale = new ArrayList<>();

        List<Step> steps = stepService.getStepsFromPattern(musiCache.sequentialScaleNameToPattern(scaleType));
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

    public Scale generateScaleFromDegrees(String scalePattern, String startingNote, int numOctaves) {
        return generateScale(scalePattern, startingNote, numOctaves);
    }

    public String getScalePattern(String scaleName) {
        return musiCache.sequentialScaleNameToPattern(scaleName.toLowerCase());
    }

    @Override
    public List<String> listAvailable() {
        return musiCache.availableScales().stream().toList();
    }
}