package com.step.forum.exception;

public class InactiveStatusException extends Exception {

    String message = null;

    public InactiveStatusException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
