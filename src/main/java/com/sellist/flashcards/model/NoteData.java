package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class NoteData {
    private Integer note;
    private Integer accidental;
    private String name;
    private String clef;
    private Integer octave;

    public static NoteData generateNewNoteData() {
        return new NoteData();
    }
}
