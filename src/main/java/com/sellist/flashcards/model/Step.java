package com.sellist.flashcards.model;

import lombok.Data;

@Data
public class Step {
    private String stepName;
    private String shortName;
    private int size;
    private int modifier;
    private int degree;
    private String quality;

    public Step(String stepName, String shortName, int stepSize, int modifier, int degree) {
        this.stepName = stepName;
        this.shortName = shortName;
        this.size = stepSize;
        this.modifier = modifier;
        this.degree = degree;
        this.quality = stepName.substring(0, 1);
    }

    @Override
    public String toString() {
        return shortName;
    }

}
