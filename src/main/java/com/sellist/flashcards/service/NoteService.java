package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteService implements ProvideInfo {

    private final MemoryCacheProvider cache;

    @Autowired
    public NoteService(MemoryCacheProvider cacheProvider) {
        this.cache = cacheProvider;
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

    public Note generateNoteByMidiValue(int midiValue, int modifier) {
        //        Currently does not support double sharps or flats
        if (modifier >= 1) {
            return new Note(midiToSharpNoteName(midiValue),midiValue);
        } else if (modifier <= -1) {
            return new Note(midiToFlatNoteName(midiValue),midiValue);
        } else {
            return new Note(midiToNaturalNoteName(midiValue),midiValue);
        }
    }

    public Note flattenNote(Note note) {
        return generateNoteByMidiValue(note.getMidiValue(), note.getModifier()-1);
    }

    public Note sharpenNote(Note note) {
        return generateNoteByMidiValue(note.getMidiValue(), note.getModifier()+1);
    }

    public String midiToSharpNoteName(int midiValue) {
        String output;
        output = cache.noteCache.midiToSharpName.get(midiValue);
        if (output == null) {
            output = midiToNaturalNoteName(midiValue);
        }

        return output;
    }

    public String midiToFlatNoteName(int midiValue) {
        String output;
        output = cache.noteCache.midiToFlatName.get(midiValue);
        if (output == null) {
            output = midiToNaturalNoteName(midiValue);
        }

        return output;
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

    private int naturalNoteToMidi(String note) {
        return cache.noteCache.naturalNameToMidi.get(note);
    }

    @Override
    public List<String> getAvailable() {
        return cache.noteCache.naturalNameToMidi.keySet().stream().toList();
    }
}
