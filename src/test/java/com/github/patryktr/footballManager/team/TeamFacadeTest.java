package com.github.patryktr.footballManager.team;

import com.github.patryktr.footballManager.team.model.CreateNewTeamDto;
import com.github.patryktr.footballManager.team.teamRole.TeamRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.github.patryktr.footballManager.team.TeamFacade.CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION;
import static org.mockito.ArgumentMatchers.any;

class TeamFacadeTest {

    private static final String DUP_NAME = "EXISTING";
    private static final String NEW_NAME = "NEW";
    private static final Long USER_ID = 1L;

    @Test
    public void shouldThrowExceptionOnTeamNameDuplicationTest() {
        //given
        TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
        TeamRoleRepository teamRoleRepository = Mockito.mock(TeamRoleRepository.class);
        Mockito.when(teamRepository.findByName(DUP_NAME)).thenReturn(Optional.of(new Team()));
        TeamFacade teamFacade = new TeamFacade(teamRepository, teamRoleRepository);
        CreateNewTeamDto existingTeamDto = new CreateNewTeamDto(DUP_NAME, USER_ID);

        //when
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> teamFacade.create(existingTeamDto));
        //then
        Assertions.assertEquals(CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION, runtimeException.getMessage());
    }

    @Test
    public void shouldCreateTeamTest() {
        //given
        TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
        TeamRoleRepository teamRoleRepository = Mockito.mock(TeamRoleRepository.class);
        Mockito.when(teamRepository.findByName(NEW_NAME)).thenReturn(Optional.empty());
        TeamFacade teamFacade = new TeamFacade(teamRepository, teamRoleRepository);
        CreateNewTeamDto existingTeamDto = new CreateNewTeamDto(DUP_NAME, USER_ID);

        //when
        //then
        teamFacade.create(existingTeamDto);

    }


}