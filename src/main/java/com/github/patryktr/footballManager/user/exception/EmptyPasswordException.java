package com.github.patryktr.footballManager.user.exception;

public class EmptyPasswordException extends RuntimeException {

    public static final String EMPTY = "Password cannot be empty.";

    public EmptyPasswordException() {
        super(EMPTY);
    }
}
