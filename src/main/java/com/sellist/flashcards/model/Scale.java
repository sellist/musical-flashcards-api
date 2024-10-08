package com.sellist.flashcards.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Scale implements Serializable {
    private List<Note> notes;
    private ScaleInfo scaleInfo;

    public Scale(List<Note> scale, ScaleInfo scaleInfo) {
        this.notes = scale;
        this.scaleInfo = scaleInfo;
    }

    public Note get(int index) {
        return notes.get(index);
    }

    public int size() {
        return notes.size();
    }
}
