package com.github.patryktr.footballManager.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;

    public User() {
    }

    public User(Long id, String name, String surname, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public static User of(CreateNewUserDto createNewUserDto) {
        return new User(null, createNewUserDto.getName(), createNewUserDto.getSurname(), createNewUserDto.getLogin(),
                createNewUserDto.getPassword(), createNewUserDto.getEmail());
    }
    public UserViewDto toViewDto() {
        return new UserViewDto(id,name,surname);
    }
}
