package com.github.patryktr.footballManager.team;

import com.github.patryktr.footballManager.team.model.AddMemberDto;
import com.github.patryktr.footballManager.team.model.CreateNewTeamDto;
import com.github.patryktr.footballManager.team.model.TeamViewDto;
import com.github.patryktr.footballManager.team.model.UpdateTeamDto;
import com.github.patryktr.footballManager.team.teamRole.TeamRole;
import com.github.patryktr.footballManager.team.teamRole.TeamRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamFacade {
    public static final String CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION = "you can't create a team,because it exist";
    private final TeamRepository teamRepository;
    private final TeamRoleRepository teamRoleRepository;

    public TeamFacade(TeamRepository teamRepository, TeamRoleRepository teamRoleRepository) {
        this.teamRepository = teamRepository;
        this.teamRoleRepository = teamRoleRepository;
    }


    public void delete(long id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public void create(CreateNewTeamDto createNewTeamDto) {
        teamRepository.findByName(createNewTeamDto.name()).ifPresent(team -> {
            throw new RuntimeException(CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION);
        });
        Team team = Team.of(createNewTeamDto);
        teamRepository.save(team);
        TeamRole teamRole = TeamRole.addTeamLeader(team.getId(), createNewTeamDto.userId());
        teamRoleRepository.save(teamRole);


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

    public void addMember(AddMemberDto addMemberDto) {
        TeamRole teamRole = TeamRole.addTeamMember(addMemberDto.userId(), addMemberDto.teamId());
        teamRoleRepository.save(teamRole);
    }


}
