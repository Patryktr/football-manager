package com.github.patryktr.footballManager.team;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("team")
public class TeamController {
    private final TeamFacade teamFacade;

    public TeamController(TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }

    @PostMapping
    public void create(@RequestBody CreateNewTeamDto createNewTeamDto) {
        teamFacade.create(createNewTeamDto);
    }

    @DeleteMapping
    public void delete(@RequestParam long id) {
        teamFacade.delete(id);
    }

    @GetMapping(value = "/byName")
    public Team getTeamByName(@RequestParam String name) {
        return teamFacade.findTeamByName(name);
    }

    @PatchMapping
    public void update(@RequestBody UpdateTeamDto updateTeamDto) {
        teamFacade.update(updateTeamDto);
    }

    @GetMapping(value = "/list")
    public List<TeamViewDto> getAllTeam() {
        return teamFacade.getAll();
    }
}
