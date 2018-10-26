package com.step.forum.exception;

public class DuplicateEmailException extends Exception {

    String message = null;

    public DuplicateEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
