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
    private int transposition;
    private Note transposedNote;

    public Note(String note, int midiValue) {
        String[] startingNoteSplit = note.split("(?<=^.)|(?=\\d)");

        this.noteName = startingNoteSplit[0];
        if (note.contains("##")) {
            this.modifier = 2;
        } else if (note.contains("#")) {
            this.modifier = 1;
        } else if (note.contains("bb")) {
            this.modifier = -2;
        } else if (note.contains("b")) {
            this.modifier = -1;
        } else {
            this.modifier = 0;
        }
        this.octave = startingNoteSplit.length > 1 ? Integer.parseInt(startingNoteSplit[startingNoteSplit.length - 1]) : 0;
        this.midiValue = midiValue;
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
        StringBuilder sb = new StringBuilder();
        sb.append(noteName);
        if (modifier == 1) {
            sb.append("#");
        } else if (modifier == -1) {
            sb.append("b");
        } else if (modifier == 2) {
            sb.append("##");
        } else if (modifier == -2) {
            sb.append("bb");
        }
        sb.append(octave);
        return sb.toString();
    }
}
