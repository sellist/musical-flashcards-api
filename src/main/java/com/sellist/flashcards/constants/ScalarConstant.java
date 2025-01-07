package com.sellist.flashcards.constants;

import java.util.List;

public class ScalarConstant  {

    private final String[] values;


    public ScalarConstant(String value) {
        if (!value.matches("^[a-zA-Z]+(,[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("Value must be a series of characters delimited by commas");
        }
        this.values = value.split(",");
    }

    public String[] toArray() {
        return values;
    }

    @Override
    public String toString() {
        return String.join(",", values);
    }

    public boolean contains(String value) {
        for (String val : values) {
            if (val.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static ScalarConstant of(String value) {
        return new ScalarConstant(value);
    }

    public List<String> toList() {
        return List.of(values);
    }

}
