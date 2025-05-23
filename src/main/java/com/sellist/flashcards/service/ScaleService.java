package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.MusiCache;
import com.sellist.flashcards.constants.Steps;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.ScaleOptions;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.model.request.ScaleRequest;
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

    public Scale generateScale(ScaleRequest req) {
        String scalePattern = req.getScaleName();
        String startingNote = req.getScaleTonic();
        int numOctaves = req.getOctaves();

        List<Note> notes = buildScale(scalePattern, startingNote, numOctaves);
        Scale scale = new Scale();
        scale.setNotes(notes);
        scale.setScaleOptions(new ScaleOptions(scalePattern, noteService.generateNote(startingNote), numOctaves));
        return scale;
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
        List<Note> scale = new ArrayList<>();

        for (int octave = 0; octave < numOctaves; octave++) {
            for (Step step : steps) {
                scale.add(stepService.stepUp(startNote, step));
            }
            if (octave < numOctaves - 1) {
                startNote = stepService.stepUp(startNote, Steps.PERFECT_OCTAVE);
            }
        }
        scale.add(stepService.stepUp(startNote, Steps.PERFECT_OCTAVE));

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

    @Override
    public List<String> listAvailable() {
        return musiCache.availableScales().stream().toList();
    }
}