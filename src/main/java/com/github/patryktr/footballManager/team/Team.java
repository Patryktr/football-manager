package com.github.patryktr.footballManager.team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int playersQuantity;


    public Team() {
    }

    private Team(Long id, String name, int playersQuantity) {
        this.id = id;
        this.name = name;
        this.playersQuantity = playersQuantity;

    }

    public static Team of(CreateNewTeamDto createNewTeamDto) {
        return new Team(null, createNewTeamDto.getName(), 0);
    }

    public TeamViewDto toViewDto() {
        return new TeamViewDto(id, name, playersQuantity);
    }

    public void applyPathChanges(UpdateTeamDto updateTeamDto) {
        if (updateTeamDto.name() != null) {
            this.name = updateTeamDto.name();
        }
    }


}
