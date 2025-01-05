package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.MusiCache;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteService {

    private final MusiCache musiCache;

    @Autowired
    public NoteService(MusiCache musiCache) {
        this.musiCache = musiCache;
    }

    public int getMidiValue(String note) {
        if (note.contains("##")) {
            return musiCache.doubleSharpASPNToMidi(note);
        } else if (note.contains("bb")) {
            return musiCache.doubleFlatASPNToMidi(note);
        } else if (note.contains("#")) {
            return musiCache.sharpASPNToMidi(note);
        } else if (note.contains("b")) {
            return musiCache.flatASPNToMidi(note);
        } else {
            return musiCache.naturalASPNToMidi(note);
        }
    }

    public int getOctaveFromMidi(int midi) {
        return (midi / 12) - 1;
    }

    public Note generateNote(String noteName) {
        if (!noteName.matches(".*\\d$")) {
            throw new IllegalArgumentException("Note must contain an octave designator: " + noteName);
        }
        return new Note(noteName, getMidiValue(noteName));
    }

    public Note generateNoteByMidiValue(int midiValue, int modifier, boolean enharmonic) {
        if (modifier == 2) {
            return new Note(musiCache.midiToDoubleSharpASPN(midiValue), midiValue);
        } else if (modifier == -2) {
            return new Note(musiCache.midiToDoubleFlatASPN(midiValue), midiValue);
        } else if (modifier == 1) {
            return new Note(musiCache.midiToSharpASPN(midiValue), midiValue);
        } else if (modifier == -1) {
            return new Note(musiCache.midiToFlatASPN(midiValue), midiValue);
        } else if (modifier == 0) {
            return new Note(musiCache.midiToNaturalASPN(midiValue), midiValue);
        } else {
            throw new NullPointerException("Note not found for midi value: " + midiValue + ", modifier: " + modifier + ", enharmonic: " + enharmonic);
        }
    }

    public Note modifyNote(Note note, int modifier) {
        return generateNoteByMidiValue(note.getMidiValue() + modifier, modifier);
    }

    public Note generateNoteByMidiValue(int midiValue, int modifier) {
        return generateNoteByMidiValue(midiValue, modifier, false);
    }

}
