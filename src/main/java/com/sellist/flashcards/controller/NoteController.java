package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.NoteService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(
            path = "/"
    )
    public ResponseEntity<Note> getNoteFromName(@RequestBody String note) {
        Note response = noteService.generateNote(note);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
