package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteFactory {

    private final NoteService noteService;

    @Autowired
    public NoteFactory(NoteService noteService) {
        this.noteService = noteService;
    }

    public Note create(String note) {
        return new Note(note, noteService.getMidiValue(note));
    }
}
