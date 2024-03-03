package com.sellist.flashcards.util;

import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.NoteData;
import com.sellist.flashcards.model.Scale;
import org.springframework.stereotype.Service;

@Service
public class NoteValidationUtil {

    public boolean isNoteInInstrumentRange(NoteData note, Instrument instrument) {
        return true;
    }

    public boolean isNoteValid(NoteData note) {
        return isNoteValid(note.getNote());
    }

//    public boolean isNoteValid(String note) {
//        return isNoteValid(MidiNoteUtil.convertNoteStringToMidi(note));
//    }

    public boolean isNoteValid(Integer note) {
        return note >= 21 && note <= 127;
    }

    public boolean isNoteInScale(NoteData note, Scale scale) {
        return true;
    }
}
