package com.sellist.flashcards.model.request;

import lombok.Data;

@Data
public class CardsRequest {
    private String startingNote;
    private String scaleType;
    private int octaves;
    private String instrument;
}
