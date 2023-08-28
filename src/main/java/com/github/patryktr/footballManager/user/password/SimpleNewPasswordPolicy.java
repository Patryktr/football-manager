package com.github.patryktr.footballManager.user.password;

import com.github.patryktr.footballManager.common.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SimpleNewPasswordPolicy implements NewPasswordPolicy {

    public static final String YOUR_PASSWORD_IS_TOO_WEEK = "Your password should include one uppercase letter, one lowercase letter, a number, and be at least 8 characters long.";
    private static final String REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public ValidationResult validate(String password) {
        if (password == null) {
            return ValidationResult.error(YOUR_PASSWORD_IS_TOO_WEEK);
        }
        Matcher matcher = Pattern.compile(REGEX).matcher(password);
        return matcher.find() ? ValidationResult.VALID : ValidationResult.error(YOUR_PASSWORD_IS_TOO_WEEK);
    }
}
