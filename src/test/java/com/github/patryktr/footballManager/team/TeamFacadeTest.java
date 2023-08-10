package com.github.patryktr.footballManager.team;

import com.github.patryktr.footballManager.team.model.CreateNewTeamDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.github.patryktr.footballManager.team.TeamFacade.CANNOT_CREATE_TEAM_WITH_SAME_NAME_EXCEPTION;

class TeamFacadeTest {

    private static final String DUP_NAME = "EXISTING";
    private static final String NEW_NAME = "NEW";

    @Test
    public void shouldThrowExceptionOnTeamNameDuplicationTest() {
        //given
        TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
        Mockito.when(teamRepository.findByName(DUP_NAME)).thenReturn(Optional.of(new Team()));
        TeamFacade teamFacade = new TeamFacade(teamRepository);
        CreateNewTeamDto existingTeamDto = new CreateNewTeamDto(DUP_NAME);

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
        Mockito.when(teamRepository.findByName(NEW_NAME)).thenReturn(Optional.empty());
        TeamFacade teamFacade = new TeamFacade(teamRepository);
        CreateNewTeamDto existingTeamDto = new CreateNewTeamDto(DUP_NAME);

        //when
        //then
        teamFacade.create(existingTeamDto);

    }


}