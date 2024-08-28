package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class ScaleInfo {
    private Note root;
    private String scaleType;
    private int numOctaves;

    public ScaleInfo(String scalePattern, Note startingNote, int numOctaves) {
        this.root = startingNote;
        this.scaleType = scalePattern;
        this.numOctaves = numOctaves;
    }
}
