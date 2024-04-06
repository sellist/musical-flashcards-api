package com.sellist.flashcards.model.request;

import lombok.Data;

@Data
public class ScaleRequest {
    private String tonic;
    private String scaleType;
    private int octave;
}
