package com.github.patryktr.footballManager.common;

public class ValidationResult {
    public final static ValidationResult VALID = new ValidationResult(null);
    private final String errorMessage;

    private ValidationResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ValidationResult error(String errorMessage) {
        return new ValidationResult(errorMessage);
    }

    public boolean isValid() {
        return errorMessage == null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
