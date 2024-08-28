package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.request.CardsRequest;
import com.sellist.flashcards.model.request.NotesNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final AbcJsService abcJsService;
    private final ScaleService scaleService;

    @Autowired
    public CardService(AbcJsService abcJsService, ScaleService scaleService) {
        this.abcJsService = abcJsService;
        this.scaleService = scaleService;
    }

    public List<Card> generateCards(CardsRequest requests) {
        List<Card> output = new ArrayList<>();

        Scale s = scaleService.generateScale(
            scaleService.getScalePattern(requests.getScaleType()),
                requests.getStartingNote(),
                requests.getOctaves()
        );

        s.getNotes().forEach(n -> output.add(generateCard(n)));

        return output;
    }

    public List<Card> generateCards(List<CardsRequest> requests) {
        List<Card> output = new ArrayList<>();
        for (CardsRequest request : requests) {
            output.addAll(generateCards(request));
        }
        return output;
    }

    public Card generateCard(Note note) {
        return new Card(abcJsService.generateAbcJs(note), note.toString());
    }
}
