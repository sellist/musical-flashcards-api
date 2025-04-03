package com.sellist.flashcards.constants;

import com.sellist.flashcards.model.Step;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@SuppressWarnings("unused")
public class Steps {

    public static final Step HALF_STEP = new Step( "Half Step", "H", 1, 0,1);
    public static final Step WHOLE_STEP = new Step( "Whole Step", "W", 2, 0,1);

    // standard steps
    public static final Step PERFECT_UNISON = new Step( "Perfect Unison", "P1", 0, 0, 0);
    public static final Step MINOR_SECOND = new Step( "Minor Second", "m2", 1, -1,1);
    public static final Step MAJOR_SECOND = new Step( "Major Second", "M2", 2,0,1);
    public static final Step MINOR_THIRD = new Step( "Minor Third", "m3", 3, -1,2);
    public static final Step MAJOR_THIRD = new Step( "Major Third", "M3", 4, 0,2);
    public static final Step PERFECT_FOURTH = new Step( "Perfect Fourth", "P4", 5, 0,3);
    public static final Step TRITONE = new Step( "Tritone", "TT", 6, -1,4);
    public static final Step PERFECT_FIFTH = new Step( "Perfect Fifth", "P5", 7, 0,4);
    public static final Step MINOR_SIXTH = new Step("Minor Sixth", "m6", 8, -1,5);
    public static final Step MAJOR_SIXTH = new Step("Major Sixth", "M6", 9, 0,5);
    public static final Step MINOR_SEVENTH = new Step( "Minor Seventh", "m7", 10, -1,6);
    public static final Step MAJOR_SEVENTH = new Step( "Major Seventh", "M7", 11, 0,6);
    public static final Step PERFECT_OCTAVE = new Step("Perfect Octave", "P8", 12, 0,7);
    public static final Step MINOR_NINTH = new Step("Minor Ninth", "m9", 13, -1,8);
    public static final Step MAJOR_NINTH = new Step("Major Ninth", "M9", 14, 0 ,8);
    public static final Step MINOR_TENTH = new Step("Minor Tenth", "m10", 15, -1 ,9);
    public static final Step MAJOR_TENTH = new Step("Major Tenth", "M10", 16, 0 ,9);
    public static final Step PERFECT_ELEVENTH = new Step("Perfect Eleventh", "P11", 17, 0 ,10);
    public static final Step AUGMENTED_ELEVENTH = new Step("Augmented Eleventh", "A11", 18, 1 ,10);
    public static final Step PERFECT_TWELFTH = new Step("Perfect Twelfth", "P12", 19, 0 ,11);
    public static final Step MINOR_THIRTEENTH = new Step("Minor Thirteenth", "m13", 20, -1 ,12);
    public static final Step MAJOR_THIRTEENTH = new Step("Major Thirteenth", "M13", 21, 0 ,12);
    public static final Step MINOR_FOURTEENTH = new Step("Minor Fourteenth", "m14", 22, -1 ,13);
    public static final Step MAJOR_FOURTEENTH = new Step("Major Fourteenth", "M14", 23, 0 ,13);
    public static final Step DOUBLE_OCTAVE = new Step("Double Octave", "P15", 24, 0 ,14);

    // uncommon steps
    public static final Step AUGMENTED_UNISON = new Step("Augmented Unison", "A1", 1, 1,0);
    public static final Step DIMINISHED_SECOND = new Step("Diminished Second", "d2", 0, -1,1);
    public static final Step AUGMENTED_SECOND = new Step("Augmented Second", "A2", 3,1,1);
    public static final Step DIMINISHED_THIRD = new Step("Diminished Third", "d3", 2, -2,2);
    public static final Step AUGMENTED_THIRD = new Step("Augmented Third", "A3", 5, 1,2);
    public static final Step DIMINISHED_FOURTH = new Step("Diminished Fourth", "d4", 4, -1,3);
    public static final Step AUGMENTED_FOURTH = new Step("Augmented Fourth", "A4", 6, 1,3);
    public static final Step DIMINISHED_FIFTH = new Step("Diminished Fifth", "d5", 6, -1,4);
    public static final Step AUGMENTED_FIFTH = new Step("Augmented Fifth", "A5", 8, 1,4);
    public static final Step DIMINISHED_SIXTH = new Step("Diminished Sixth", "d6", 7, -2,5);
    public static final Step AUGMENTED_SIXTH = new Step("Augmented Sixth", "A6", 10, 1,5);
    public static final Step DIMINISHED_SEVENTH = new Step("Diminished Seventh", "d7", 9, -2,6);
    public static final Step AUGMENTED_SEVENTH = new Step("Augmented Seventh", "A7", 12, 1,6);
    public static final Step DIMINISHED_OCTAVE = new Step("Diminished Octave", "d8", 11, -1,7);


    public static List<Step> getAllSteps() {
        List<Step> steps = new ArrayList<>();
        for (Field field : Steps.class.getDeclaredFields()) {
            try {
                steps.add((Step) field.get(null));
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return steps;
    }

    public static List<Step> getStandardSteps() {
        return Arrays.asList(PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD,
                MAJOR_THIRD, PERFECT_FOURTH, TRITONE, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH,
                MAJOR_SEVENTH, PERFECT_OCTAVE, MINOR_NINTH, MAJOR_NINTH, MINOR_TENTH, MAJOR_TENTH, PERFECT_ELEVENTH,
                PERFECT_TWELFTH, MINOR_THIRTEENTH, MAJOR_THIRTEENTH, MINOR_FOURTEENTH,
                MAJOR_FOURTEENTH, DOUBLE_OCTAVE);
    }

    public static List<Step> getUncommonSteps() {
        return Arrays.asList(AUGMENTED_UNISON, DIMINISHED_SECOND, AUGMENTED_SECOND, DIMINISHED_THIRD,
                AUGMENTED_THIRD, DIMINISHED_FOURTH, AUGMENTED_FOURTH, DIMINISHED_FIFTH, AUGMENTED_FIFTH,
                DIMINISHED_SIXTH, AUGMENTED_SIXTH, DIMINISHED_SEVENTH, AUGMENTED_SEVENTH, DIMINISHED_OCTAVE);
    }
}
