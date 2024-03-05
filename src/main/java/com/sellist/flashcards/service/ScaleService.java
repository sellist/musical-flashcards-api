package com.sellist.flashcards.service;

import com.sellist.flashcards.model.MidiNote;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.util.MidiNoteUtil;
import com.sellist.flashcards.util.StepStrToIntUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ScaleService {

    @Autowired
    private MidiNoteUtil midiNoteUtil;

    public boolean isNoteInScale(ABCNoteData ABCNoteData, Scale scale) {
        return false;
    }

    private List<String> generateSharpScale(String scalePattern, String startingNote, int numOctaves) {
        List<String> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        scale.add(midiNoteUtil.midiToSharpNoteName(currentMidiValue));
        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                currentMidiValue += StepStrToIntUtil.stepStrToInt(String.valueOf(scalePattern.charAt(i)));
                scale.add(midiNoteUtil.midiToSharpNoteName(currentMidiValue));
            }
        }

        return scale;
    }

    private List<String> generateFlatScale(String scalePattern, String startingNote, int numOctaves) {
        List<String> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        scale.add(midiNoteUtil.midiToFlatNoteName(currentMidiValue));
        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                currentMidiValue += StepStrToIntUtil.stepStrToInt(String.valueOf(scalePattern.charAt(i)));
                scale.add(midiNoteUtil.midiToFlatNoteName(currentMidiValue));
            }
        }

        return scale;
    }

    public List<String> generateScale(String scalePattern, String startingNote, int numOctaves) {
        String[] startingNoteSplit = startingNote.split("(?<=\\D)(?=\\d)");
        String startingNoteName = startingNoteSplit[0];

        Set<String> sharpKeys = Set.of("C", "G", "D", "A", "E", "B", "F#","C#");
        if (sharpKeys.contains(startingNoteName)) {
            return generateSharpScale(scalePattern, startingNote, numOctaves);
        } else {
            return generateFlatScale(scalePattern, startingNote, numOctaves);
        }
    }

    public List<String> generateScale(String scalePattern, MidiNote startingNote, int numOctaves) {
        return generateScale(scalePattern, startingNote.getNoteName(), numOctaves);
    }

}
