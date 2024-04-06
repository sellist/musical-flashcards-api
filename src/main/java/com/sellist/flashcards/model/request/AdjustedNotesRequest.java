package com.sellist.flashcards.model.request;

import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Note;
import lombok.Data;

import java.util.List;

@Data
public class AdjustedNotesRequest {
    private Instrument instrument;
    private List<Note> notes;
}
