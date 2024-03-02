package com.sellist.flashcards.service;

import com.sellist.flashcards.model.NoteData;
import com.sellist.flashcards.model.Scale;

import java.util.List;

public class  ScaleService {

    public boolean isNoteInScale(NoteData noteData, Scale scale) {
        return false;
    }

    public List<NoteData> getScaleNotes(Scale scale, NoteData tonic) {
        return null;
    }

    public List<NoteData> getScaleNotes(Scale scale, NoteData tonic, Integer octaves) {
        return null;
    }

    private int[] getScaleIntervals(Scale scale) {
        return null;
    }
}
