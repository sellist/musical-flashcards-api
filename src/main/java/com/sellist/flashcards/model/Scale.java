package com.sellist.flashcards.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Scale implements Serializable {
    private List<Note> notes;
    private ScaleOptions scaleOptions;

    public Scale(List<Note> scale, ScaleOptions scaleOptions) {
        this.notes = scale;
        this.scaleOptions = scaleOptions;
    }

    public Note get(int index) {
        return notes.get(index);
    }

    public int size() {
        return notes.size();
    }
}
