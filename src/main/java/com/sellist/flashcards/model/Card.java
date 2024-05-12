package com.sellist.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    public String front;
    public String back;
}
