package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Instrument {
    private String name;
    private String lowNote;
    private String highNote;
    // note name of what middle C written sounds
    private String transposition;
    private String clef;
    private String family;

    public static Instrument generateNewInstrumentData() {
        return new Instrument();
    }
}
