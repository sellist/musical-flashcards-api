package com.sellist.flashcards.service;

import com.sellist.flashcards.constants.AbcJsConstants;
import com.sellist.flashcards.model.Note;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AbcJsService {

    private final AbcJsConstants constants;

    public AbcJsService(AbcJsConstants abcJsConstants) {
        this.constants = abcJsConstants;
    }

    public String generateAbcJs(Note note) {

        String modifier;
        if (note.getModifier() < 0) {
            modifier = new String(new char[Math.abs(note.getModifier())]).replace("\0", constants.FLAT_MARKER);
        } else if (note.getModifier() > 0) {
            modifier = new String(new char[note.getModifier()]).replace("\0", constants.SHARP_MARKER);
        } else {
            modifier = "";
        }

        String octavePart;
        if (note.getOctave() > 4) {
            octavePart = new String(new char[Math.abs(4 - note.getOctave())]).replace("\0", "'");
        } else if (note.getOctave() < 4) {
            octavePart = new String(new char[Math.abs(4 - note.getOctave())]).replace("\0", ",");
        } else {
            octavePart = "";
        }

        return modifier + note.getNoteName().toUpperCase(Locale.ROOT) + octavePart;
    }
}
