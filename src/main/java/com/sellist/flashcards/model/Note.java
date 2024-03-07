package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Note {
    private int midiValue;
    private String noteName;

    public Note(int midiValue, String noteName) {
        this.midiValue = midiValue;
        this.noteName = noteName;
    }

    public Note(String note) {
        String[] startingNoteSplit = note.split("(?<=\\D)(?=\\d)");
        this.noteName = startingNoteSplit[0];
        this.midiValue = startingNoteSplit.length > 1 ? Integer.parseInt(startingNoteSplit[1]) : 0;
    }
}
