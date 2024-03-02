package com.sellist.flashcards.util;

public class StepStrToIntUtil {
    public static Integer stepStrToInt(String stepStr) {
        switch (stepStr) {
            case "W":
                return 2;
            case "H":
                return 1;
            case "A":
                return 3;
            case "U":
                return 0;
        }
        return 0;
    }
}
