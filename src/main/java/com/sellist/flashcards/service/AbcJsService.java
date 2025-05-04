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
            modifier = new String(new char[Math.abs(note.getModifier())]).replace("\0", AbcJsConstants.FLAT_MARKER);
        } else if (note.getModifier() > 0) {
            modifier = new String(new char[note.getModifier()]).replace("\0", AbcJsConstants.SHARP_MARKER);
        } else {
            modifier = AbcJsConstants.EMPTY;
        }

        String octavePart;
        if (note.getOctave() > 4) {
            octavePart = new String(new char[Math.abs(4 - note.getOctave())]).replace("\0", AbcJsConstants.OCTAVE_UP_MARKER);
        } else if (note.getOctave() < 4) {
            octavePart = new String(new char[Math.abs(4 - note.getOctave())]).replace("\0", AbcJsConstants.OCTAVE_DOWN_MARKER);
        } else {
            octavePart = AbcJsConstants.EMPTY;
        }

        return modifier + note.getNoteName().toUpperCase(Locale.ROOT) + octavePart;
    }
}
