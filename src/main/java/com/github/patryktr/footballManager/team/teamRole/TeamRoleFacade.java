package com.github.patryktr.footballManager.team.teamRole;

import com.github.patryktr.footballManager.team.model.AddMemberDto;
import org.springframework.stereotype.Service;

@Service
public class TeamRoleFacade {
    private final TeamRoleRepository teamRoleRepository;


    public TeamRoleFacade(TeamRoleRepository teamRoleRepository) {
        this.teamRoleRepository = teamRoleRepository;
    }

    public void addMember(AddMemberDto addMemberDto) {
        TeamRole teamRole = new TeamRole(null, addMemberDto.userId(), addMemberDto.teamId(), Role.MEMBER);
        teamRoleRepository.save(teamRole);
    }
}
