package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.CardRequest;
import com.sellist.flashcards.model.request.NotesNameRequest;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final MemoryCacheProvider cache;
    private final NoteService noteService;
    private final AbcJsService abcJsService;

    @Autowired
    public CardService(NoteService noteService, MemoryCacheProvider cacheProvider, AbcJsService abcJsService) {
        this.cache = cacheProvider;
        this.noteService = noteService;
        this.abcJsService = abcJsService;
    }

    public List<Card> generateCards(CardRequest cardRequest) {
        List<Card> output = new ArrayList<>();
        for (Note note : cardRequest.getNotes()) {
            output.add(generateCard(note));
        }
        return output;
    }

    public List<Card> generateCards(NotesNameRequest notes) {
        List<Card> output = new ArrayList<>();
        for (String note : notes.getNotes()) {
            output.add(generateCard(noteService.generateNote(note)));
        }
        return output;
    }

    public Card generateCard(Note note) {
        return new Card(abcJsService.generateAbcJs(note), note.toString());
    }
}
