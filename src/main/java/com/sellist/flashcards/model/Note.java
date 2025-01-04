package com.sellist.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Note {
    private int octave;
    private String noteName;
    private int modifier;
    private int midiValue;
    private String accidental;

    public Note(String note, int midiValue) {
        String[] startingNoteSplit = note.split("(?<=^.)|(?=\\d)");
        this.noteName = startingNoteSplit[0];
        if (startingNoteSplit.length > 2) {
            this.modifier = accidentalToModifier(startingNoteSplit[1]);
        }
        this.octave = startingNoteSplit.length > 1 ? Integer.parseInt(startingNoteSplit[startingNoteSplit.length - 1]) : 0;
        this.midiValue = midiValue;
        this.accidental = modifierToAccidental(modifier);
    }

    @JsonIgnore
    public String getDebugString() {
        return "Note{" +
                "octave=" + octave +
                ", noteName='" + noteName + '\'' +
                ", modifier=" + modifier +
                ", midiValue=" + midiValue +
                '}';
    }

    @Override
    public String toString() {
        return noteName +
                modifierToAccidental(modifier) +
                octave;
    }

    public static String modifierToAccidental(int modifier) {
        return switch (modifier) {
            case 1 -> "#";
            case -1 -> "b";
            case 2 -> "##";
            case -2 -> "bb";
            default -> "";
        };
    }

    public static int accidentalToModifier(String accidental) {
        return switch (accidental) {
            case "#" -> 1;
            case "b" -> -1;
            case "##" -> 2;
            case "bb" -> -2;
            default -> 0;
        };
    }
}
