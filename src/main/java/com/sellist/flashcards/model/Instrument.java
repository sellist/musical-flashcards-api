package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Instrument implements ApiResponse {
    private String instrument;
    private String lowNote;
    private String highNote;
    private String transposition;
    private String clef;
    private String family;

}
