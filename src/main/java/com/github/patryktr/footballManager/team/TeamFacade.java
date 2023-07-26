package com.github.patryktr.footballManager.team;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamFacade {
    private final TeamRepository teamRepository;

    public TeamFacade(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public void delete(long id) {
        teamRepository.deleteTeamById(id);
    }

    public void create(CreateNewTeamDto createNewTeamDto) {
        teamRepository.findByName(createNewTeamDto.getName()).ifPresent(team -> {
            throw new RuntimeException("you can't create a team,cause it exist");
        });
        Team team = Team.of(createNewTeamDto);
        teamRepository.save(team);

    }

    public Team findTeamByName(String name) {
        return teamRepository.findByName(name).orElse(null);
    }

    public void update(UpdateTeamDto updateTeamDto) {
        Team team = teamRepository.findById(updateTeamDto.id()).orElseThrow(() -> {
            throw new RuntimeException("you can't update a team,cause it isn't exist");
        });
        team.applyPathChanges(updateTeamDto);
    }

    public List<TeamViewDto> getAll() {
        return teamRepository.findAll().stream()
                .map(Team::toViewDto)
                .collect(Collectors.toList());
    }

}
