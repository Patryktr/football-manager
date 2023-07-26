package com.github.patryktr.footballManager.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("You can't create a user,because user with the same email exist");
    }
}
