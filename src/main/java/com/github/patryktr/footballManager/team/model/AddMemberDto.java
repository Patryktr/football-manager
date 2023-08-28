package com.github.patryktr.footballManager.team.model;

import com.github.patryktr.footballManager.team.teamRole.Role;

public record AddMemberDto(long userId, long teamId, long memberId) {
}
