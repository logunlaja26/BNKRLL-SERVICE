package com.bnkrll.exceptions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException() {
        super();
    }

    public SessionNotFoundException(String message) {
        super(message);
    }
}
