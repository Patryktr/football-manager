package com.github.patryktr.footballManager.team;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException() {
        super("you can't update a team,because it isn't exist");
    }
}
