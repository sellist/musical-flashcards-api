package com.sellist.flashcards.util;

import org.springframework.stereotype.Service;

@Service
public class MidiNoteUtil {
    public static Integer convertNoteStringToMidi(String note) {
        String[] parts = note.split("");
        String noteName = parts[0];
        String octave = parts[parts.length - 1];
        String modifier = parts.length == 3 ? parts[1] : "";



        NoteValidationUtil noteValidationUtil = new NoteValidationUtil();



        return 0;
    }
}
