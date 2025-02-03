package com.sellist.flashcards.exception;

public class UnavailableNoteException extends RuntimeException {
    public UnavailableNoteException(String message) {
        super(message);
    }
}
