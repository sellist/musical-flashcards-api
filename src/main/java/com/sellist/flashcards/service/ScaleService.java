package com.sellist.flashcards.service;

import com.sellist.flashcards.model.NoteData;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.util.MidiNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class  ScaleService {
    @Autowired
    private MidiNoteUtil midiNoteUtil;

    public boolean isNoteInScale(NoteData noteData, Scale scale) {
        return false;
    }

    public List<NoteData> getScaleNotes(Scale scale, NoteData tonic) {
        return null;
    }

    public List<NoteData> getScaleNotes(Scale scale, NoteData tonic, Integer octaves) {
        return null;
    }

    public List<Integer> generateScale(String scalePattern, int startingMidiValue, int numOctaves) {
        List<Integer> scale = new ArrayList<>();
        int currentMidiValue = startingMidiValue;

        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                char step = scalePattern.charAt(i);
                if (step == 'W') {
                    currentMidiValue += 2; // Move up a whole step
                } else if (step == 'H') {
                    currentMidiValue += 1; // Move up a half step
                }
                scale.add(currentMidiValue);
            }
        }

        return scale;
    }

    private List<String> generateSharpScale(String scalePattern, String startingNote, int numOctaves) {
        List<String> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                char step = scalePattern.charAt(i);
                if (step == 'W') {
                    currentMidiValue += 2;
                } else if (step == 'H') {
                    currentMidiValue += 1;
                }
                scale.add(midiNoteUtil.midiToSharpNoteName(currentMidiValue));
            }
        }

        return scale;
    }

    private List<String> generateFlatScale(String scalePattern, String startingNote, int numOctaves) {
        List<String> scale = new ArrayList<>();
        int currentMidiValue = midiNoteUtil.getMidiValue(startingNote);

        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                char step = scalePattern.charAt(i);
                if (step == 'W') {
                    currentMidiValue += 2;
                } else if (step == 'H') {
                    currentMidiValue += 1;
                }
                scale.add(midiNoteUtil.midiToFlatNoteName(currentMidiValue));
            }
        }

        return scale;
    }

    public List<String> generateScale(String scalePattern, String startingNote, int numOctaves) {
        if (Arrays.asList("C", "G", "D", "A", "E", "B", "F#","C#").contains(startingNote)) {
            return generateSharpScale(scalePattern, startingNote, numOctaves);
        } else {
            return generateFlatScale(scalePattern, startingNote, numOctaves);
        }
    }

}
