package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.util.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ScaleService {

    @Autowired
    private NoteUtil noteUtil;

    public List<Note> generateMidiScale(String scalePattern, String startingNote, int numOctaves) {
        List<Note> scale = new ArrayList<>();
        List<Integer> midiValues = new ArrayList<>();

        int currentMidiValue = noteUtil.getMidiValue(startingNote);

        midiValues.add(currentMidiValue);
        for (int octave = 0; octave < numOctaves; octave++) {
            for (int i = 0; i < scalePattern.length(); i++) {
                midiValues.add(currentMidiValue);
            }
        }

        return scale;
    }
}
