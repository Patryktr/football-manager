package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.user.model.CreateNewUserDto;
import com.github.patryktr.footballManager.user.model.UserViewDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacade {
    private final UserRepository userRepository;

    public UserFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void create(CreateNewUserDto createNewUserDto) {
        userRepository.findByEmail(createNewUserDto.getEmail()).ifPresent(user -> {
            throw new UserNotFoundException();
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

    public void changePassword() {
        ChangePasswordValidator.Result validateResult = ChangePasswordValidator.validate("1u1", "101000");
        if (validateResult.isValid()) {


        }
    }


}
