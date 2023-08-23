package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.common.ValidationResult;
import com.github.patryktr.footballManager.user.exception.EmptyPasswordException;
import com.github.patryktr.footballManager.user.exception.IncorrectPasswordException;
import com.github.patryktr.footballManager.user.exception.SamePasswordException;
import com.github.patryktr.footballManager.user.model.CreateNewUserDto;
import com.github.patryktr.footballManager.user.password.NewPasswordPolicy;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserCredential {
    @Id
    private String login;
    private String password;

    public UserCredential() {
    }

    public UserCredential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static UserCredential of(CreateNewUserDto createNewUserDto, NewPasswordPolicy newPasswordPolicy) {
        ValidationResult validate = newPasswordPolicy.validate(createNewUserDto.getPassword());
        if (!validate.isValid()) {
            throw new IllegalArgumentException(validate.getErrorMessage());
        }
        return new UserCredential(createNewUserDto.getLogin(), createNewUserDto.getPassword());
    }

    public void applyPathChanges(String oldPassword, String newPassword, NewPasswordPolicy newPasswordPolicy) {
        if (!this.password.equals(oldPassword)) {
            throw new IncorrectPasswordException();
        }
        if (newPassword == null) {
            throw new EmptyPasswordException();
        }
        if (oldPassword.equals(newPassword)) {
            throw new SamePasswordException();
        }
        newPasswordPolicy.validate(password);
        this.password = newPassword;
    }

    public String getPassword() {
        return password;
    }
}
