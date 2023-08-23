package com.github.patryktr.footballManager.user.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String DO_NOT_EXIST = "User with this login do not exist";

    public UserNotFoundException() {
        super(DO_NOT_EXIST);
    }
}
