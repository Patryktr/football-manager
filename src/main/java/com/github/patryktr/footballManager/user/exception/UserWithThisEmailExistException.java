package com.github.patryktr.footballManager.user.exception;

public class UserWithThisEmailExistException extends RuntimeException {

    public UserWithThisEmailExistException() {
        super("You can't create a user,because user with the same email exist");
    }
}
