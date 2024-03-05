package com.sellist.flashcards.util;

import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.MidiNote;
import org.springframework.stereotype.Service;

@Service
public class NoteValidationUtil {

    public boolean isNoteInInstrumentRange(ABCNoteData note, Instrument instrument) {
        return true;
    }

    public boolean isNoteValid(ABCNoteData note) {
        return isNoteValid(note.getNote());
    }

    public boolean isNoteValid(Integer note) {
        return note >= 21 && note <= 127;
    }

    public boolean isNoteValid(MidiNote note) {
        return isNoteValid(note.getMidiValue());
    }

    public boolean isNoteValid(String note) {
        return isNoteValid(new MidiNote(note));
    }
}
