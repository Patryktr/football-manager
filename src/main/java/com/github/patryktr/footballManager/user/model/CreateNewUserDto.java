package com.github.patryktr.footballManager.user.model;


public record CreateNewUserDto(String name, String surname, String login, String password, String email) {

}
