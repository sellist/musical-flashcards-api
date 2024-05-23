package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.CardsRequest;
import com.sellist.flashcards.model.request.NotesNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final NoteService noteService;
    private final AbcJsService abcJsService;
    private final ScaleService scaleService;

    @Autowired
    public CardService(NoteService noteService, AbcJsService abcJsService, ScaleService scaleService) {
        this.noteService = noteService;
        this.abcJsService = abcJsService;
        this.scaleService = scaleService;
    }

    public List<Card> generateCards(CardsRequest cardsRequest) {
        List<Card> output = new ArrayList<>();

        scaleService.generateScale(
            scaleService.getScalePattern(cardsRequest.getScaleType()),
            cardsRequest.getStartingNote(),
            cardsRequest.getOctaves()
        ).forEach(note -> output.add(generateCard(note)));

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
