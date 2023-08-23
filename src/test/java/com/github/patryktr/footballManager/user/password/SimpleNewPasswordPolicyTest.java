package com.github.patryktr.footballManager.user.password;

import com.github.patryktr.footballManager.common.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleNewPasswordPolicyTest {

    @Test
    public void shouldThrowToWeekPasswordException() {
        //when
        SimpleNewPasswordPolicy simpleNewPasswordPolicy = new SimpleNewPasswordPolicy();
        ValidationResult validationResult = simpleNewPasswordPolicy.validate("qsr1");
        //then
        Assertions.assertEquals(SimpleNewPasswordPolicy.YOUR_PASSWORD_IS_TOO_WEEK, validationResult.getErrorMessage());
    }
    @Test
    public void shouldThrowToWeekPasswordException2() {
        //when
        SimpleNewPasswordPolicy simpleNewPasswordPolicy = new SimpleNewPasswordPolicy();
        ValidationResult validationResult = simpleNewPasswordPolicy.validate(null);
        //then
        Assertions.assertEquals(SimpleNewPasswordPolicy.YOUR_PASSWORD_IS_TOO_WEEK, validationResult.getErrorMessage());
    }
}