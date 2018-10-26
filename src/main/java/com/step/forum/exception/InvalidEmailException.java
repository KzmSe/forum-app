package com.step.forum.exception;

public class InvalidEmailException extends Exception {

    String message = null;

    public InvalidEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
