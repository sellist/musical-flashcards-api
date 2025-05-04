package com.sellist.flashcards.constants;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class Scales {

    private Scales() {}

    public static final String MAJOR = "1,2,3,4,5,6,7";
    public static final String MINOR = "1,2,b3,4,5,b6,b7";
    public static final String ACOUSTIC = "1,2,3,#4,5,6,b7";
    public static final String AEOLIAN = "1,2,b3,4,5,b6,b7";
    public static final String ALGERIAN = "1,2,b3,#4,5,b6,7";
    public static final String ALTERED = "1,b2,b3,b4,b5,b6,b7";
    public static final String AUGMENTED = "1,b3,3,5,#5,7";
    public static final String BLUES = "1,b3,4,b5,5,b7";
    public static final String FLAT_CHROMATIC = "1,b2,2,b3,3,4,b5,5,b6,6,b7,7";
    public static final String SHARP_CHROMATIC = "1,#1,2,#2,3,4,#4,5,#5,6,#6,7";
    public static final String DORIAN = "1,2,b3,4,5,6,b7";
    public static final String DOUBLE_DIMINISHED = "1,b2,3,4,5,b6,7";
    public static final String ENIGMATIC = "1,b2,3,#4,#5,6,7";
    public static final String FLAMENCO = "1,b2,3,4,5,b6,7";
    public static final String HALF_DIMINISHED = "1,2,b3,4,b5,b6,b7";
    public static final String HARMONIC_MAJOR = "1,2,3,4,5,b6,7";
    public static final String HARMONIC_MINOR = "1,2,b3,4,5,b6,7";
    public static final String HUNGARIAN_MINOR = "1,2,b3,#4,5,b6,7";
    public static final String HUNGARIAN_MAJOR = "1,#2,3,#4,5,6,b7";
    public static final String IONIAN = "1,2,3,4,5,6,7";
    public static final String LOCRIAN = "1,b2,b3,4,b5,b6,b7";
    public static final String LYDIAN = "1,2,3,#4,5,6,7";
    public static final String LYDIAN_AUGMENTED = "1,2,3,#4,#5,6,7";
    public static final String BEBOP = "1,2,3,4,5,b6,6,7";
    public static final String MAJOR_LOCRIAN = "1,2,3,4,b5,b6,b7";
    public static final String MINOR_PENTATONIC = "1,b3,4,5,b7";
    public static final String MIXOLYDIAN = "1,2,3,4,5,6,b7";
    public static final String NEAPOLITAN_MAJOR = "1,b2,b3,4,5,6,7";
    public static final String NEAPOLITAN_MINOR = "1,b2,b3,4,5,b6,7";
    public static final String PERSIAN = "1,b2,3,4,b5,b6,7";
    public static final String PHRYGIAN = "1,b2,b3,4,5,b6,b7";
    public static final String PHRYGIAN_DOMINANT = "1,b2,3,4,5,b6,b7";
    public static final String TRITONE = "1,b2,3,b5,5,b7";
    public static final String WHOLE_TONE = "1,2,3,#4,#5,#6";

    public static String getScale(String scaleKey) {
        try {
            Field field = Scales.class.getField(scaleKey.toUpperCase());
            return (String) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}