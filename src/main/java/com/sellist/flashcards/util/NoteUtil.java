package com.sellist.flashcards.util;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.src.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteUtil {

    private final CacheProvider cache;

    @Autowired
    public NoteUtil(CacheProvider cacheProvider) {
        this.cache = cacheProvider;
    }

    public String midiToSharpNoteName(int midiValue) {
        return cache.noteCache.midiToSharpNameMap.get(midiValue);
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

    public int getOctaveFromMidi(int midi) {
        return (midi / 12) - 1;
    }

    public Note generateNote(String noteName) {
        return new Note(noteName, getMidiValue(noteName));
    }

    public Note generateNoteByMidiValue(int midiValue, String accidental) {
        if (accidental.equals("b")) {
            return new Note(midiToFlatNoteName(midiValue),midiValue);
        } else if (accidental.equals("#")) {
            return new Note(midiToSharpNoteName(midiValue),midiValue);
        } else {
            return new Note(midiToNaturalNoteName(midiValue),midiValue);
        }
    }

    public String midiToFlatNoteName(int midiValue) {
        return cache.noteCache.midiToFlatNameMap.get(midiValue);
    }

    public String midiToNaturalNoteName(int midiValue) {
        return cache.noteCache.midiToNaturalNameMap.get(midiValue);
    }

    private int flatNoteToMidi(String note) {
        return cache.noteCache.flatNameToMidiMap.get(note);
    }

    private int sharpNoteToMidi(String note) {
        return cache.noteCache.sharpNameToMidiMap.get(note);
    }

    private int naturalNoteToMidi(String note) {
        return cache.noteCache.naturalNameToMidiMap.get(note);
    }

}
