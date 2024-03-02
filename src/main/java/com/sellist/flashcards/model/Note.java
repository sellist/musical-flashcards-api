package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Note {
    private Integer note;
    private String name;
    private String clef;
    private Integer octave;
    private String absJsRepresenation;
}
