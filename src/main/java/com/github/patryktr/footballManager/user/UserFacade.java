package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.user.exception.UserNotFoundException;
import com.github.patryktr.footballManager.user.exception.UserWithThisEmailExistException;
import com.github.patryktr.footballManager.user.model.CreateNewUserDto;
import com.github.patryktr.footballManager.user.model.UserViewDto;
import com.github.patryktr.footballManager.user.password.NewPasswordPolicy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacade {
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final NewPasswordPolicy newPasswordPolicy;

    public UserFacade(UserRepository userRepository, UserCredentialRepository userCredentialRepository, NewPasswordPolicy newPasswordPolicy) {
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.newPasswordPolicy = newPasswordPolicy;
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void create(CreateNewUserDto createNewUserDto) {
        userRepository.findByEmail(createNewUserDto.getEmail()).ifPresent(user -> {
            throw new UserWithThisEmailExistException();
        });
        User user = User.of(createNewUserDto);
        userRepository.save(user);
    }

    public List<UserViewDto> getAll() {
        return userRepository.findAll().stream()
                .map(User::toViewDto)
                .collect(Collectors.toList());
    }

    public UserViewDto findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        return user == null ? null : user.toViewDto();
    }

    public void changePassword(String login, String oldPassword, String newPassword) {
        UserCredential userCredential = userCredentialRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userCredential.applyPathChanges(oldPassword, newPassword, newPasswordPolicy);
        userCredentialRepository.save(userCredential);
    }
}
