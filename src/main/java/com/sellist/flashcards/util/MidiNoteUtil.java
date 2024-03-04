package com.sellist.flashcards.util;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MidiNoteUtil {
    String[] sharpNoteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    String[] flatNoteNames = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

    @Autowired
    @Qualifier("sharpsNameToMidiMap")
    private Map<String, Integer> sharpsNameToMidiMap;

    @Autowired
    @Qualifier("flatsNameToMidiMap")
    private Map<String, Integer> flatsNameToMidiMap;

    public MidiNoteUtil() {
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
        return flatsNameToMidiMap.get(note);
    }

    private int sharpNoteToMidi(String note) {
        return sharpsNameToMidiMap.get(note);
    }
}
