package org.miage.procrastinapp.exception;

public class TacheNotFoundException extends RuntimeException {
    public TacheNotFoundException(String message) {
        super(message);
    }
}
