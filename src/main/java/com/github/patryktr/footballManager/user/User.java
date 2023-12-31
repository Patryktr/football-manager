package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.user.model.CreateNewUserDto;
import com.github.patryktr.footballManager.user.model.UpdatePasswordUserDto;
import com.github.patryktr.footballManager.user.model.UserViewDto;
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

    private String email;

    public User() {
    }

    public User(Long id, String name, String surname, String login, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;

        this.email = email;
    }

    public static User of(CreateNewUserDto createNewUserDto) {
        return new User(null, createNewUserDto.name(), createNewUserDto.surname(), createNewUserDto.login(),
                createNewUserDto.email());
    }

    public UserViewDto toViewDto() {
        return new UserViewDto(id, name, surname);
    }

  /*  public void applyPathChanges(UpdatePasswordUserDto updatePasswordUserDto) {
        if (updatePasswordUserDto.getId() != null) {
            if (updatePasswordUserDto.getPassword().equals(password)) {
                this.password = updatePasswordUserDto.getNewPassword();
            }
        }
    }*/
}
