package com.sellist.flashcards.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ScaleOptions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Note root;
    private String scaleType;
    private int numOctaves;

    public ScaleOptions(String scalePattern, Note startingNote, int numOctaves) {
        this.root = startingNote;
        this.scaleType = scalePattern;
        this.numOctaves = numOctaves;
    }


}
