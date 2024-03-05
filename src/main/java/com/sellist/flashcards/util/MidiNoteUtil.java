package com.sellist.flashcards.util;

import com.sellist.flashcards.model.MidiNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MidiNoteUtil {
    String[] sharpNoteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    String[] flatNoteNames = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

    @Autowired
    @Qualifier("sharpsNameToMidiMap")
    private Map<String, Integer> sharpsNameToMidiMap;

    @Autowired
    @Qualifier("flatsNameToMidiMap")
    private Map<String, Integer> flatsNameToMidiMap;

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

    public MidiNote getMidiNoteFromString(String note) {
        return new MidiNote(note);
    }
}
