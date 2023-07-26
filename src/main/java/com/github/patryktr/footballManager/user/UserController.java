package com.github.patryktr.footballManager.user;

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
    public void create(@RequestBody CreateNewUserDto createNewUserDto){
        userFacade.create(createNewUserDto);

    }
    @DeleteMapping
    public void delete(@RequestParam long id){
        userFacade.delete(id);
    }
    @GetMapping(value = "/list")
    public List<UserViewDto> getAll(){
        return userFacade.getAll();
    }

}
