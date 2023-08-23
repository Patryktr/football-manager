package com.github.patryktr.footballManager.user.api;

import com.github.patryktr.footballManager.user.UserFacade;
import com.github.patryktr.footballManager.user.model.CreateNewUserDto;
import com.github.patryktr.footballManager.user.model.UpdatePasswordUserDto;
import com.github.patryktr.footballManager.user.model.UserViewDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public void create(@RequestBody CreateNewUserDto createNewUserDto) {
        userFacade.create(createNewUserDto);

    }

    @DeleteMapping
    public void delete(@RequestParam long id) {
        userFacade.delete(id);
    }

    @GetMapping(value = "/list")
    public List<UserViewDto> getAll() {
        return userFacade.getAll();
    }

    @GetMapping(value = "/byId")
    public UserViewDto getAll(long id) {
        return userFacade.findById(id);
    }

    @PatchMapping
    public void changePassword(UpdatePasswordUserDto updatePasswordUserDto) {
        userFacade.changePassword(
                updatePasswordUserDto.login(),
                updatePasswordUserDto.password(),
                updatePasswordUserDto.newPassword()
        );
    }


}
