package com.sellist.flashcards.util;

public class StepStrToIntUtil {
    public static Integer stepStrToInt(String stepStr) {
        return switch (stepStr) {
            case "W" -> 2;
            case "H" -> 1;
            case "A" -> 3;
            default -> 0;
        };
    }
}
