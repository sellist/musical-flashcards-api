package com.sellist.flashcards.util;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NoteUtil {
    String[] sharpNoteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    String[] flatNoteNames = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

    private final CacheProvider cache;

    @Autowired
    public NoteUtil(CacheProvider cacheProvider) {
        this.cache = cacheProvider;
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
        } else if (note.contains("b")) {
            return flatNoteToMidi(note);
        } else {
            return naturalNoteToMidi(note);
        }
    }

    public Note generateNote(String noteName) {
        return new Note(getMidiValue(noteName), noteName);
    }

    public Note generateNoteByMidiValue(int midiValue, String accidental) {
        if (accidental.equals("b")) {
            return new Note(midiValue, midiToFlatNoteName(midiValue));
        } else if (accidental.equals("#")) {
            return new Note(midiValue, midiToSharpNoteName(midiValue));
        } else {
            return new Note(midiValue, midiToSharpNoteName(midiValue));
        }
    }

    private int flatNoteToMidi(String note) {
        return cache.flatNameToMidiMap.get(note);
    }

    private int sharpNoteToMidi(String note) {
        return cache.sharpNameToMidiMap.get(note);
    }

    private int naturalNoteToMidi(String note) { return cache.naturalNameToMidiMap.get(note); }

}
