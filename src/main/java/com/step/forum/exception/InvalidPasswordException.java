package com.step.forum.exception;

public class InvalidPasswordException extends Exception {

    String message = null;

    public InvalidPasswordException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
