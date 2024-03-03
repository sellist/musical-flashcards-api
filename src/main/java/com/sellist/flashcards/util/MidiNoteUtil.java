package com.sellist.flashcards.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MidiNoteUtil {
    String[] sharpNoteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    String[] flatNoteNames = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

    private Map<String, Integer> sharpNoteMap;

    private Map<String, Integer> flatNoteMap;

    public MidiNoteUtil(@Qualifier("sharpsNameToMidiMap") Map<String, Integer> sharpNoteMap,
                        @Qualifier("flatsNameToMidiMap") Map<String, Integer> flatNoteMap) {
        this.sharpNoteMap = sharpNoteMap;
        this.flatNoteMap = flatNoteMap;
        System.out.println(midiToFlatNoteName(40));
    }

    public String midiToSharpNoteName(int midiValue) {

        int noteIndex = (midiValue) % 12;
        int octave = (midiValue) / 12 - 1;

        return sharpNoteNames[noteIndex] + octave;
    }

    public String midiToFlatNoteName(int midiValue) {

        int noteIndex = (midiValue) % 12;
        int octave = (midiValue) / 12 - 1;

        return flatNoteNames[noteIndex] + octave;
    }

    public int getMidiValue(String note) {
        if (note.contains("#")) {
            return sharpNoteToMidi(note);
        } else {
            return flatNoteToMidi(note);
        }
    }

    private int flatNoteToMidi(String note) {
        return flatNoteMap.get(note);
    }

    private int sharpNoteToMidi(String note) {
        return sharpNoteMap.get(note);
    }
}
