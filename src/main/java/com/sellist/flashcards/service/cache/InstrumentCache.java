package com.sellist.flashcards.service.cache;

import com.sellist.flashcards.model.Instrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class InstrumentCache {
    public Map<String, Instrument> nameToInstruments;
}
