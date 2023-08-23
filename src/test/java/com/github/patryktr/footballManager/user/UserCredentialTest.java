package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.common.ValidationResult;
import com.github.patryktr.footballManager.user.exception.EmptyPasswordException;
import com.github.patryktr.footballManager.user.exception.IncorrectPasswordException;
import com.github.patryktr.footballManager.user.exception.SamePasswordException;
import com.github.patryktr.footballManager.user.password.NewPasswordPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class UserCredentialTest {
    NewPasswordPolicy newPasswordPolicy = Mockito.mock(NewPasswordPolicy.class);

    @BeforeEach
    void setUp() {
        Mockito.when(newPasswordPolicy.validate(any())).thenReturn(ValidationResult.VALID);
    }

    @Test
    public void shouldThrowExceptionOfTheSamePasswordTest() {
        //given
        UserCredential userCredential = new UserCredential("login", "aSdF1@3$");
        //when
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> userCredential.applyPathChanges("aSdF1@3$", "aSdF1@3$", newPasswordPolicy));
        //then
        Assertions.assertEquals(SamePasswordException.OLD_PASSWORD_AND_NEW_PASSWORD_ARE_THE_SAME, runtimeException.getMessage());
        ;
    }

    @Test
    public void shouldThrowIncorrectPasswordExceptionTest() {
        //given
        UserCredential userCredential = new UserCredential("login", "aSdF1@2$");
        //when
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> userCredential.applyPathChanges("aSdF1@3$", "aSdF1@3$", newPasswordPolicy));
        //then
        Assertions.assertEquals(IncorrectPasswordException.PASSWORD_IS_INCORRECT, runtimeException.getMessage());
        ;
    }

    @Test
    public void shouldThrowEmptyPasswordExceptionTest() {
        //given
        UserCredential userCredential = new UserCredential("login", "aSdF1@3$");
        //when
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> userCredential.applyPathChanges("aSdF1@3$", null, newPasswordPolicy));
        //then
        Assertions.assertEquals(EmptyPasswordException.EMPTY, runtimeException.getMessage());
        ;
    }

    @Test
    public void shouldValidateTest() {
        //given
        UserCredential userCredential = new UserCredential("login", "PlMjbhxz");
        //when
        userCredential.applyPathChanges("PlMjbhxz", "AsDssss2", newPasswordPolicy);
        //then
        Assertions.assertEquals("AsDssss2", userCredential.getPassword());

    }

}