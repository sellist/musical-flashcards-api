package com.sellist.flashcards.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Step {
    private String stepName;
    private String shortName;
    private int size;
    private int mod;
    private int degree;
    private String quality;

    public Step(String stepName, String shortName, int stepSize, int mod, int degree) {
        this.stepName = stepName;
        this.shortName = shortName;
        this.size = stepSize;
        this.mod = mod;
        this.degree = degree;
        this.quality = stepName.substring(0, 1);
    }

    @Override
    public String toString() {
        return shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return size == step.size && mod == step.mod && degree == step.degree && Objects.equals(stepName, step.stepName) && Objects.equals(shortName, step.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepName, shortName, size, mod, degree);
    }
}
