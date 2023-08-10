package com.github.patryktr.footballManager.team;

import com.github.patryktr.footballManager.team.model.CreateNewTeamDto;
import com.github.patryktr.footballManager.team.model.TeamViewDto;
import com.github.patryktr.footballManager.team.model.UpdateTeamDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamFacade {
    public static final String CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION = "you can't create a team,because it exist";
    private final TeamRepository teamRepository;

    public TeamFacade(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public void delete(long id) {
        teamRepository.deleteById(id);
    }

    public void create(CreateNewTeamDto createNewTeamDto) {
        teamRepository.findByName(createNewTeamDto.name()).ifPresent(team -> {
            throw new RuntimeException(CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION);
        });
        Team team = Team.of(createNewTeamDto);
        teamRepository.save(team);

    }

    public TeamViewDto findTeamByName(String name) {
        Team team = teamRepository.findByName(name).orElse(null);
        return team == null ? null : team.toViewDto();
    }

    public void update(UpdateTeamDto updateTeamDto) {
        Team team = teamRepository.findById(updateTeamDto.id())
                .orElseThrow(TeamNotFoundException::new);
        team.applyPathChanges(updateTeamDto);
        teamRepository.save(team);
    }

    public List<TeamViewDto> getAll() {
        return teamRepository.findAll().stream()
                .map(Team::toViewDto)
                .collect(Collectors.toList());
    }

}
