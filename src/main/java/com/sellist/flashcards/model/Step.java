package com.sellist.flashcards.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Step {
    private String stepName;
    private String shortName;
    private int size;

    public Step(String stepName, String shortName, int stepSize) {
        this.stepName = stepName;
        this.shortName = shortName;
        this.size = stepSize;
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
        return size == step.size && Objects.equals(stepName, step.stepName) && Objects.equals(shortName, step.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepName, shortName, size);
    }
}
