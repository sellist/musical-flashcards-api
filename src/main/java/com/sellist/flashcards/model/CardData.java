package com.sellist.flashcards.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardData extends ApiResponse {
    private Instrument instrument;
    private NoteData noteData;
}
