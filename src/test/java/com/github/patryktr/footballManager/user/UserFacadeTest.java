package com.github.patryktr.footballManager.user;


import com.github.patryktr.footballManager.user.password.NewPasswordPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.github.patryktr.footballManager.user.exception.UserNotFoundException.DO_NOT_EXIST;


class UserFacadeTest {
    private static final String OLD_PASSWORD = "PkIE124";
    private static final String LOGIN = "login";
    private static final String OTHER_LOGIN = "otherLogin";
    private static final String NEW_PASSWORD = "UA2a4h";
    UserCredentialRepository userCredentialRepository = Mockito.mock(UserCredentialRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    NewPasswordPolicy newPasswordPolicy = Mockito.mock(NewPasswordPolicy.class);


    @BeforeEach
    void setUp() {
        Mockito.when(userCredentialRepository.findById(LOGIN)).thenReturn(Optional.of(new UserCredential(LOGIN, OLD_PASSWORD)));
    }

    @Test
    public void shouldChangePassword() {
        //given
        UserFacade userFacade = new UserFacade(userRepository, userCredentialRepository, newPasswordPolicy);
        //when
        // then
        userFacade.changePassword(LOGIN, OLD_PASSWORD, NEW_PASSWORD);

    }

    @Test
    public void shouldThrowExceptionIncorrectPasswordTest() {

        //given
        UserFacade userFacade = new UserFacade(userRepository, userCredentialRepository, newPasswordPolicy);
        //when
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> userFacade.changePassword(OTHER_LOGIN, OLD_PASSWORD, NEW_PASSWORD));

        //then
        Assertions.assertEquals(DO_NOT_EXIST, runtimeException.getMessage());

    }


}