package com.github.patryktr.footballManager.user;

import lombok.Getter;

@Getter
public class CreateNewUserDto {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
}
