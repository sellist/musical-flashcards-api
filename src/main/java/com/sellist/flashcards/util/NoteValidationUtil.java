package com.sellist.flashcards.util;

import com.sellist.flashcards.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteValidationUtil {

    public boolean isNoteValid(Integer note) {
        return note >= 21 && note <= 127;
    }

    public boolean isNoteValid(Note note) {
        return isNoteValid(note.getMidiValue());
    }

    public boolean isNoteValid(String note) {
        return isNoteValid(new Note(note));
    }
}
