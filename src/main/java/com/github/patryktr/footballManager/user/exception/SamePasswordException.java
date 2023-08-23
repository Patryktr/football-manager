package com.github.patryktr.footballManager.user.exception;

public class SamePasswordException extends RuntimeException {
public static final String OLD_PASSWORD_AND_NEW_PASSWORD_ARE_THE_SAME ="You can't change your password because old password and new password are the same";
    public SamePasswordException() {
        super(OLD_PASSWORD_AND_NEW_PASSWORD_ARE_THE_SAME);
    }
}
