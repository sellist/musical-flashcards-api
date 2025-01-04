package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class ScaleOptions {
    private Note root;
    private String scaleType;
    private int numOctaves;

    public ScaleOptions(String scalePattern, Note startingNote, int numOctaves) {
        this.root = startingNote;
        this.scaleType = scalePattern;
        this.numOctaves = numOctaves;
    }
}
