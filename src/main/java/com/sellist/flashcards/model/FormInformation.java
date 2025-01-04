package com.sellist.flashcards.model;

import lombok.Data;

import java.util.List;

@Data
public class FormInformation {
    private List<String> notes;
    private List<String> scales;
    private List<String> steps;
}
