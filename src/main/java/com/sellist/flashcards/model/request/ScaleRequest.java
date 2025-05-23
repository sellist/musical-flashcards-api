package com.sellist.flashcards.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScaleRequest {
    @JsonProperty("scaleTonic")
    private String scaleTonic;

    @JsonProperty("scaleName")
    private String scaleName;

    @JsonProperty("octaves")
    private int octaves;
}
