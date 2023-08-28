package com.github.patryktr.footballManager.team.teamRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TeamRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long teamId;

    private Role teamRole;

    public TeamRole() {
    }


    public TeamRole(Long id, Long userId, Long teamId, Role teamRole) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
        this.teamRole = teamRole;
    }
    public static TeamRole addTeamLeader(Long userId, Long teamId) {
        return new TeamRole(null, userId, teamId, Role.LEADER);
    }
    public static TeamRole addTeamMember(Long userId, Long teamId) {
        return new TeamRole(null, userId, teamId, Role.MEMBER);
    }

}
