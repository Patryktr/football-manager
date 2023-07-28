package com.github.patryktr.footballManager.user.model;

import lombok.Getter;

@Getter
public class UpdatePasswordUserDto {
    Long id;
    String password;
    String newPassword;
}
