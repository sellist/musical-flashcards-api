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
        if (note.contains("#")) {
            return sharpNoteToMidi(note);
        } else if (note.contains("b")) {
            return flatNoteToMidi(note);
        } else {
            return naturalNoteToMidi(note);
        }
    }

    public Note getFlatEnharmonic(Note note) {
        return generateNoteByMidiValue(note.getMidiValue(), note.getModifier()-1);
    }

    public Note getSharpEnharmonic(Note note) {
        return generateNoteByMidiValue(note.getMidiValue(), note.getModifier()+1);
    }

    public Note getEnharmonic(Note note, int modifier) {
        return generateNoteByMidiValue(note.getMidiValue(), modifier);
    }

    public int getOctaveFromMidi(int midi) {
        return (midi / 12) - 1;
    }

    public Note generateNote(String noteName) {
        return new Note(noteName, getMidiValue(noteName));
    }

    public Note generateNoteByMidiValue(int midiValue, int modifier) {
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
            throw new IllegalArgumentException("Invalid modifier");
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

    public String midiToDoubleSharpNoteName(int midiValue) {
        String output;
        output = cache.noteCache.midiToDoubleSharpName.get(midiValue);
        if (output == null) {
            output = midiToNaturalNoteName(midiValue);
        }
        return output;
    }

    public String midiToDoubleFlatNoteName(int midiValue) {
        String output;
        output = cache.noteCache.midiToDoubleFlatName.get(midiValue);
        if (output == null) {
            output = midiToNaturalNoteName(midiValue);
        }
        return output;
    }

    public String getDegreeOfScale(String note, String scale) {
        return cache.noteCache.scaleNameToPattern.get(scale).split(",")[getMidiValue(note) % 12];
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
    public List<String> listAvailable() {
        return cache.noteCache.naturalNameToMidi.keySet().stream().toList();
    }
}
