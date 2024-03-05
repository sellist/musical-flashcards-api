package com.sellist.flashcards.service;

import com.sellist.flashcards.model.MidiNote;
import com.sellist.flashcards.util.MidiNoteUtil;
import com.sellist.flashcards.util.StepStrToIntUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ScaleService {


    private final Set<String> sharpKeys = Set.of("C", "G", "D", "A", "E", "B", "F#","C#");
    private final Set<String> flatKeys = Set.of("F", "Bb", "Eb", "Ab", "Db", "Gb", "Cb");

    @Autowired
    private MidiNoteUtil midiNoteUtil;

    private List<MidiNote> generateSharpScale(String scalePattern, String startingNote, int numOctaves) {
        List<MidiNote> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        scale.add(new MidiNote(midiNoteUtil.midiToSharpNoteName(currentMidiValue)));
        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                currentMidiValue += StepStrToIntUtil.stepStrToInt(String.valueOf(scalePattern.charAt(i)));
                scale.add(new MidiNote(midiNoteUtil.midiToSharpNoteName(currentMidiValue)));
            }
        }

        return scale;
    }

    private List<MidiNote> generateFlatScale(String scalePattern, String startingNote, int numOctaves) {
        List<MidiNote> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        scale.add(new MidiNote(midiNoteUtil.midiToFlatNoteName(currentMidiValue)));
        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                currentMidiValue += StepStrToIntUtil.stepStrToInt(String.valueOf(scalePattern.charAt(i)));
                scale.add(new MidiNote(midiNoteUtil.midiToFlatNoteName(currentMidiValue)));
            }
        }

        return scale;
    }

    public List<MidiNote> generateScale(String scalePattern, String startingNote, int numOctaves) {
        String[] startingNoteSplit = startingNote.split("(?<=\\D)(?=\\d)");
        String startingNoteName = startingNoteSplit[0];

        if (sharpKeys.contains(startingNoteName)) {
            return generateSharpScale(scalePattern, startingNote, numOctaves);
        } else if (flatKeys.contains(startingNoteName)) {
            return generateFlatScale(scalePattern, startingNote, numOctaves);
        } else {
            throw new IllegalArgumentException("Invalid note");
        }
    }

    public List<MidiNote> generateScale(String scalePattern, MidiNote startingNote, int numOctaves) {
        return generateScale(scalePattern, startingNote.getNoteName(), numOctaves);
    }

}
