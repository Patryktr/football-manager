package com.github.patryktr.footballManager.team;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException() {
        super("This team do not exist");
    }
}
