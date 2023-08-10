package com.github.patryktr.footballManager.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordValidator {


    public static final String SAME_PASSWORD_ERROR_MESSAGE = "You can't change your password because old password and new password are the same";
    public static final String PASSWORD_CANNOT_BE_EMPTY_ERROR_MESSAGE = "Password cannot be empty.";
    public static final String YOUR_PASSWORD_IS_TOO_WEEK = "Your password should include one uppercase letter, one lowercase letter, a number, and be at least 8 characters long.";
    private static final String REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static Result validate(String oldPassword, String newPassword) {
        if (oldPassword == null || newPassword == null) {
            return Result.error(PASSWORD_CANNOT_BE_EMPTY_ERROR_MESSAGE);
        }
        if (oldPassword.equals(newPassword)) {
            return Result.error(SAME_PASSWORD_ERROR_MESSAGE);
        }
        Matcher matcher = Pattern.compile(REGEX).matcher(newPassword);
        boolean isValid = matcher.find();

        if (!isValid) {
            return Result.error(YOUR_PASSWORD_IS_TOO_WEEK);
        }
        return Result.VALID;
    }

    public static class Result {
        private final static Result VALID = new Result(null);
        private final String errorMessage;

        private Result(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        private static Result error(String errorMessage) {
            return new Result(errorMessage);
        }

        public boolean isValid() {
            return errorMessage == null;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }


}
