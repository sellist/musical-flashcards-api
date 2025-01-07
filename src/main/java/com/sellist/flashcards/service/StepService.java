package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.MusiCache;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StepService implements ProvideApiInfo {

    private final NoteService noteService;

    private final MusiCache musiCache;

    @Autowired
    public StepService(MusiCache musiCache, NoteService noteService) {
        this.noteService = noteService;
        this.musiCache = musiCache;
    }

    public Step getDifference(Note note1, Note note2) {
        int midiValue1 = note1.getMidiValue();
        int midiValue2 = note2.getMidiValue();
        int difference = midiValue2 - midiValue1;
        return musiCache.intervalSizeToStep(difference);
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
        int targetOctave = noteService.getOctaveFromMidi(note.getMidiValue() + step.getSize() - note.getModifier());

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
        return handleStepDown(note, musiCache.stepNameToStep(step));
    }

    public Note stepUp(Note note, String step) {
        return handleStepUp(note, musiCache.stepNameToStep(step));
    }

    public Note stepUp(Note note, Step step) {
        return handleStepUp(note, step);
    }

    public List<Step> getStepsFromPattern(List<String> pattern) {
        List<Step> steps = new ArrayList<>();
        for (String stepName : pattern) {
            steps.add(musiCache.stepNameToStep(stepName));
        }
        return steps;
    }

    public List<Step> getStepsFromPattern(String pattern) {
        String[] patternList = pattern.split(",");
        List<Step> steps = new ArrayList<>();
        for (String stepName : patternList) {
            steps.add(musiCache.stepNameToStep(stepName));
        }
        return steps;
    }


    @Override
    public List<String> listAvailable() {
        return musiCache.availableSteps();
    }
}
