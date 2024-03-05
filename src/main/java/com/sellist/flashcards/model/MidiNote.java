package com.sellist.flashcards.model;

import com.sellist.flashcards.util.MidiNoteUtil;
import lombok.Data;

@Data
public class MidiNote {
    private int midiValue;
    private String noteName;

    public MidiNote(int midiValue, String noteName) {
        this.midiValue = midiValue;
        this.noteName = noteName;
    }

    public MidiNote(String note) {
        String[] startingNoteSplit = note.split("(?<=\\D)(?=\\d)");
        this.noteName = startingNoteSplit[0];
        this.midiValue = startingNoteSplit.length > 1 ? Integer.parseInt(startingNoteSplit[1]) : 0;
    }
}
