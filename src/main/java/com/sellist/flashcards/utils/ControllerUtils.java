package com.sellist.flashcards.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ControllerUtils {
    private ControllerUtils() {}

    public static List<String> deserialize(String rawNotes) {
        if (rawNotes == null || rawNotes.isBlank()) {
            return List.of();
        }
        String decoded = URLDecoder.decode(rawNotes, StandardCharsets.UTF_8);
        return Arrays.stream(decoded.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
