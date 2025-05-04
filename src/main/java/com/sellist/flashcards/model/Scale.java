package com.sellist.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scale implements Serializable {
    private List<Note> notes;

    private ScaleOptions scaleOptions;

    public Note get(int index) {
        return notes.get(index);
    }

    public int size() {
        return notes.size();
    }
}
