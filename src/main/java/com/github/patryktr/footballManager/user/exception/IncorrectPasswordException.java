package com.github.patryktr.footballManager.user.exception;

public class IncorrectPasswordException extends RuntimeException {

    public static final String PASSWORD_IS_INCORRECT = "You can't change password,because your password is incorrect";

    public IncorrectPasswordException() {
        super(PASSWORD_IS_INCORRECT);
    }
}
