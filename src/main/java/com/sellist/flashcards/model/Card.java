package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Card implements ApiResponse {
    private Instrument instrument;
    private Note note;
}
