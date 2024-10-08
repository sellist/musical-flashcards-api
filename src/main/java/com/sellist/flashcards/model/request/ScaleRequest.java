package com.sellist.flashcards.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScaleRequest {
    @JsonProperty("scaleTonic")
    private String scaleTonic;

    @JsonProperty("scaleType")
    private String scaleType;

    @JsonProperty("octave")
    private int octave;
}
