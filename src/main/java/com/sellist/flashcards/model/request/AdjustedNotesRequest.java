package com.sellist.flashcards.model.request;

import com.sellist.flashcards.model.Note;
import lombok.Data;

import java.util.List;

@Data
public class AdjustedNotesRequest {
    private List<Note> notes;
}
