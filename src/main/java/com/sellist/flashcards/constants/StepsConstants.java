package com.sellist.flashcards.constants;

import ch.qos.logback.classic.Logger;
import com.sellist.flashcards.model.Step;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@SuppressWarnings("unused")
public class StepsConstants {

    public final Step HALF_STEP = new Step( "Half Step", "H", 1);
    public final Step WHOLE_STEP = new Step( "Whole Step", "W", 2);

    public final Step PERFECT_UNISON = new Step( "Perfect Unison", "P1", 0);
    public final Step MINOR_SECOND = new Step( "Minor Second", "m2", 1);
    public final Step MAJOR_SECOND = new Step( "Major Second", "M2", 2);
    public final Step MINOR_THIRD = new Step( "Minor Third", "m3", 3);
    public final Step MAJOR_THIRD = new Step( "Major Third", "M3", 4);
    public final Step PERFECT_FOURTH = new Step( "Perfect Fourth", "P4", 5);
    public final Step TRITONE = new Step( "Tritone", "TT", 6);
    public final Step PERFECT_FIFTH = new Step( "Perfect Fifth", "P5", 7);
    public final Step MINOR_SIXTH = new Step("Minor Sixth", "m6", 8);
    public final Step MAJOR_SIXTH = new Step("Major Sixth", "M6", 9);
    public final Step MINOR_SEVENTH = new Step( "Minor Seventh", "m7", 10);
    public final Step MAJOR_SEVENTH = new Step( "Major Seventh", "M7", 11);
    public final Step PERFECT_OCTAVE = new Step("Perfect Octave", "P8", 12);
    public final Step MINOR_NINTH = new Step("Minor Ninth", "m9", 13);
    public final Step MAJOR_NINTH = new Step("Major Ninth", "M9", 14);
    public final Step MINOR_TENTH = new Step("Minor Tenth", "m10", 15);
    public final Step MAJOR_TENTH = new Step("Major Tenth", "M10", 16);
    public final Step PERFECT_ELEVENTH = new Step("Perfect Eleventh", "P11", 17);
    public final Step AUGMENTED_ELEVENTH = new Step("Augmented Eleventh", "A11", 18);
    public final Step PERFECT_TWELFTH = new Step("Perfect Twelfth", "P12", 19);
    public final Step MINOR_THIRTEENTH = new Step("Minor Thirteenth", "m13", 20);
    public final Step MAJOR_THIRTEENTH = new Step("Major Thirteenth", "M13", 21);

    public final Step AUGMENTED_UNISON = new Step("Augmented Unison", "A1", 1);
    public final Step DIMINISHED_SECOND = new Step("Diminished Second", "d2", 0);
    public final Step AUGMENTED_SECOND = new Step("Augmented Second", "A2", 3);
    public final Step DIMINISHED_THIRD = new Step("Diminished Third", "d3", 2);
    public final Step AUGMENTED_THIRD = new Step("Augmented Third", "A3", 5);
    public final Step DIMINISHED_FOURTH = new Step("Diminished Fourth", "d4", 4);
    public final Step AUGMENTED_FOURTH = new Step("Augmented Fourth", "A4", 6);
    public final Step DIMINISHED_FIFTH = new Step("Diminished Fifth", "d5", 6);
    public final Step AUGMENTED_FIFTH = new Step("Augmented Fifth", "A5", 8);
    public final Step DIMINISHED_SIXTH = new Step("Diminished Sixth", "d6", 7);
    public final Step AUGMENTED_SIXTH = new Step("Augmented Sixth", "A6", 9);
    public final Step DIMINISHED_SEVENTH = new Step("Diminished Seventh", "d7", 9);
    public final Step AUGMENTED_SEVENTH = new Step("Augmented Seventh", "A7", 12);
    public final Step DIMINISHED_OCTAVE = new Step("Diminished Octave", "d8", 11);


    public List<Step> getSteps() {
        List<Step> steps = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                steps.add((Step) field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return steps;
    }

}
