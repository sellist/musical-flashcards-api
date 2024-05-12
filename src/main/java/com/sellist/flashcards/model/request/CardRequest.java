package com.sellist.flashcards.model.request;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Options;
import lombok.Data;

import java.util.List;

@Data
public class CardRequest {
    private List<Note> notes;
}
