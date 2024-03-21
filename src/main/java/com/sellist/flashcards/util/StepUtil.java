package com.sellist.flashcards.util;

import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
        return cacheProvider.stepCache.intervalSizeToStepMap.get(difference);
    }

    /**
     * Business logic for taking in a note and step, and returning the note after that step
     * @param note
     * @param step
     * @return
     */
    private Note handleStepDown(Note note, Step step) {
        String[] baseNotes = {"C", "D", "E", "F", "G", "A", "B"};
        String[] baseNotesReverse = {"B", "A", "G", "F", "E", "D", "C"};

        // could cache this
        int inputNoteIndex = Arrays.binarySearch(baseNotes, note.getNoteName().substring(0, 1));

        int targetNoteIndex;
        String targetNote;
        int targetOctave;

        targetNoteIndex = (inputNoteIndex + step.getDegree()) % baseNotes.length;
        targetNote = baseNotesReverse[targetNoteIndex];
        targetOctave = noteUtil.getOctaveFromMidi(note.getMidiValue() - step.getSize());

        System.out.println("step: " + step);
        System.out.println("inputNote: " + note.getDebugString());
        System.out.println("targetNote: " + targetNote);
        System.out.println("targetOctave: " + targetOctave);

        int targetModifier = note.getModifier() + step.getModifier();


        String targetModifierString = "";
        if (targetModifier > 0) {
            targetModifierString = "#";
        } else if (targetModifier < 0) {
            targetModifierString = "b";
        }
        StringBuilder targetNoteBuilder = new StringBuilder();
        targetNoteBuilder.append(targetNote);
        targetNoteBuilder.append(new String(new char[Math.abs(targetModifier)]).replace("\0", targetModifierString));

        if (targetNote.equals("C") && targetModifier < 0) {
            targetOctave++;
        } else if (targetNote.equals("B") && targetModifier > 0) {
            targetOctave--;
        }
        targetNoteBuilder.append(targetOctave);

        Note target = noteUtil.generateNote(targetNoteBuilder.toString());

        System.out.println(target);

        return target;
    }

    private Note handleStepUp(Note note, Step step) {
        String[] baseNotes = {"C", "D", "E", "F", "G", "A", "B"};

        int inputNoteIndex = Arrays.binarySearch(baseNotes, note.getNoteName().substring(0, 1));

        int targetNoteIndex;
        String targetNote;
        int targetOctave;

        targetNoteIndex = (inputNoteIndex + step.getDegree()) % baseNotes.length;
        targetNote = baseNotes[targetNoteIndex];
        targetOctave = noteUtil.getOctaveFromMidi(note.getMidiValue() + step.getSize());

        System.out.println("inputNote: " + note.getDebugString());

        int targetModifier = note.getModifier() + step.getModifier();

        String targetModifierString = "";
        if (targetModifier > 0) {
            targetModifierString = "#";
        } else if (targetModifier < 0) {
            targetModifierString = "b";
        }

        StringBuilder targetNoteBuilder = new StringBuilder();

        targetNoteBuilder.append(targetNote);
        targetNoteBuilder.append(new String(new char[Math.abs(targetModifier)]).replace("\0", targetModifierString));

        if (targetNote.equals("C") && targetModifier < 0) {
            targetOctave++;
        } else if (targetNote.equals("B") && targetModifier > 0) {
            targetOctave--;
        }
        targetNoteBuilder.append(targetOctave);

        System.out.println(targetNoteBuilder.toString());
        Note target = noteUtil.generateNote(targetNoteBuilder.toString());
        System.out.println("outputNote" + target.getDebugString());


        return target;
    }

    public Note stepDown(Note note, String step) {
        return handleStepDown(note, getStep(step));
    }

    public Note stepUp(Note note, String step) {
        return handleStepUp(note, getStep(step));
    }

    public Step getStep(String stepName) {
        return cacheProvider.stepCache.stepNameToStepMap.get(stepName);
    }
}
