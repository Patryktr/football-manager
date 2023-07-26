package com.github.patryktr.footballManager.user;

import com.github.patryktr.footballManager.team.Team;
import com.github.patryktr.footballManager.team.TeamViewDto;
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
    public void create(CreateNewUserDto createNewUserDto){
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


}
