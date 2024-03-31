package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StepService {

    private final NoteService noteService;

    private final MemoryCacheProvider cacheProvider;

    @Autowired
    public StepService(NoteService noteService, MemoryCacheProvider cacheProvider) {
        this.noteService = noteService;
        this.cacheProvider = cacheProvider;
    }

    public Step getDifference(Note note1, Note note2) {
        int midiValue1 = note1.getMidiValue();
        int midiValue2 = note2.getMidiValue();
        int difference = midiValue2 - midiValue1;
        return cacheProvider.stepCache.intervalSizeToStep.get(difference);
    }

    private Note handleStepDown(Note note, Step step) {
        String[] baseNotes = {"C", "D", "E", "F", "G", "A", "B"};

        int inputNoteIndex = Arrays.asList(baseNotes).indexOf(note.getNoteName().substring(0, 1));

        int targetNoteIndex = (inputNoteIndex - step.getDegree() + baseNotes.length) % baseNotes.length;
        String targetNote = baseNotes[targetNoteIndex];
        int targetOctave = noteService.getOctaveFromMidi(note.getMidiValue() - step.getSize());

        Note baseDegreeNote = noteService.generateNote(targetNote + targetOctave);

        int difference = (note.getMidiValue() - step.getSize()) - baseDegreeNote.getMidiValue();

        int octaveDiff = (targetOctave - note.getOctave()) * 12;

        // handles octave differences when calculating scale degree difference
        if (difference >= 10) {
            difference = difference - octaveDiff;
        } else if (difference <= -10) {
            difference = difference + octaveDiff;
        }

        return noteService.generateNoteByMidiValue(note.getMidiValue() - step.getSize(), difference);
    }

    private Note handleStepUp(Note note, Step step) {
        String[] baseNotes = {"C", "D", "E", "F", "G", "A", "B"};

        int inputNoteIndex = Arrays.asList(baseNotes).indexOf(note.getNoteName().substring(0, 1));

        int targetNoteIndex = (inputNoteIndex + step.getDegree()) % baseNotes.length;
        String targetNote = baseNotes[targetNoteIndex];
        int targetOctave = noteService.getOctaveFromMidi(note.getMidiValue() + step.getSize());

        Note baseDegreeNote = noteService.generateNote(targetNote + targetOctave);


        int difference = (note.getMidiValue() + step.getSize()) - baseDegreeNote.getMidiValue();

        int octaveDiff = (targetOctave - note.getOctave()) * 12;

        // handles octave differences when calculating scale degree difference
        if (difference >= 10) {
            difference = difference - octaveDiff;
        } else if (difference <= -10) {
            difference = difference + octaveDiff;
        }

        return noteService.generateNoteByMidiValue(note.getMidiValue() + step.getSize(), difference);
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
        return cacheProvider.stepCache.stepNameToStep.get(stepName);
    }

    public List<Step> getStepsFromPattern(String pattern) {
        List<Step> steps = new ArrayList<>();
        for (String stepName : pattern.split(",")) {
            steps.add(getStep(stepName));
        }
        return steps;
    }
}
