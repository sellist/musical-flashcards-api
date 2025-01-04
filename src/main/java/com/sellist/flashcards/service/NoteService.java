package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteService implements ProvideApiInfo {

    private final MemoryCacheProvider cache;

    @Autowired
    public NoteService(MemoryCacheProvider cacheProvider) {
        this.cache = cacheProvider;
    }

    public int getMidiValue(String note) {
        if (note.contains("##")) {
            return doubleSharpNoteToMidi(note);
        } else if (note.contains("bb")) {
            return doubleFlatNoteToMidi(note);
        } else if (note.contains("#")) {
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

    public Note generateNoteByMidiValue(int midiValue, int modifier, boolean enharmonic) {
        if (modifier == 2) {
            return new Note(midiToDoubleSharpNoteName(midiValue), midiValue);
        } else if (modifier == -2) {
            return new Note(midiToDoubleFlatNoteName(midiValue), midiValue);
        } else if (modifier == 1) {
            return new Note(midiToSharpNoteName(midiValue), midiValue);
        } else if (modifier == -1) {
            return new Note(midiToFlatNoteName(midiValue), midiValue);
        } else if (modifier == 0) {
            return new Note(midiToNaturalNoteName(midiValue), midiValue);
        } else {
            throw new NullPointerException("Note not found for midi value: " + midiValue + ", modifier: " + modifier + ", enharmonic: " + enharmonic);
        }
    }

    public Note generateNoteByMidiValue(int midiValue, int modifier) {
        return generateNoteByMidiValue(midiValue, modifier, false);
    }

    public String midiToSharpNoteName(int midiValue) {
        return cache.noteCache.midiToSharpName.get(midiValue);
    }

    public String midiToFlatNoteName(int midiValue) {
        return cache.noteCache.midiToFlatName.get(midiValue);
    }

    public String midiToDoubleSharpNoteName(int midiValue) {
        return cache.noteCache.midiToDoubleSharpName.get(midiValue);
    }

    public String midiToDoubleFlatNoteName(int midiValue) {
        return cache.noteCache.midiToDoubleFlatName.get(midiValue);
    }

    public String midiToNaturalNoteName(int midiValue) {
        return cache.noteCache.midiToNaturalName.get(midiValue);
    }

    public String scaleNameToPattern(String scaleName) {
        return cache.noteCache.scaleNameToPattern.get(scaleName);
    }

    private int flatNoteToMidi(String note) {
        return cache.noteCache.flatNameToMidi.get(note);
    }

    private int sharpNoteToMidi(String note) {
        return cache.noteCache.sharpNameToMidi.get(note);
    }

    private int doubleFlatNoteToMidi(String note) {
        return cache.noteCache.doubleFlatNameToMidi.get(note);
    }

    private int doubleSharpNoteToMidi(String note) {
        return cache.noteCache.doubleSharpNameToMidi.get(note);
    }

    private int naturalNoteToMidi(String note) {
        return cache.noteCache.naturalNameToMidi.get(note);
    }

    @Override
    public List<String> listAvailable() {
        return cache.noteCache.naturalNameToMidi.keySet().stream().toList();
    }
}
