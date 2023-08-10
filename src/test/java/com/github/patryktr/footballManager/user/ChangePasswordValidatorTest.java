package com.github.patryktr.footballManager.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangePasswordValidatorTest {
    @Test
    public void shouldThrowExceptionOfTheSamePasswordTest(){
        // when
        ChangePasswordValidator.Result validate = ChangePasswordValidator.validate("aSdF1@3$", "aSdF1@3$");

        //then
        Assertions.assertEquals(ChangePasswordValidator.SAME_PASSWORD_ERROR_MESSAGE,validate.getErrorMessage());
    }
    @Test
    public void shouldThrowExceptionOfEmptyPasswordTest(){
        //when
        ChangePasswordValidator.Result validate = ChangePasswordValidator.validate(null, "zXcV1@3$");
        ChangePasswordValidator.Result validate2 = ChangePasswordValidator.validate("zXcV1@3$", null);
        //then
        Assertions.assertEquals(ChangePasswordValidator.PASSWORD_CANNOT_BE_EMPTY_ERROR_MESSAGE,validate.getErrorMessage());
        Assertions.assertEquals(ChangePasswordValidator.PASSWORD_CANNOT_BE_EMPTY_ERROR_MESSAGE,validate2.getErrorMessage());
    }
    @Test
    public void shouldThrowExceptionOfWeekPasswordTest(){
        //when
        ChangePasswordValidator.Result validate = ChangePasswordValidator.validate("aBc20394", " zbsr2");
        //then
        Assertions.assertEquals(ChangePasswordValidator.YOUR_PASSWORD_IS_TOO_WEEK,validate.getErrorMessage());
    }
    @Test
    public void ShouldValidateTest(){
        //when
        ChangePasswordValidator.Result validate = ChangePasswordValidator.validate("PlMjbhxz", "AsDssss2");
        //then
        Assertions.assertNull(validate.getErrorMessage());
    }
}
