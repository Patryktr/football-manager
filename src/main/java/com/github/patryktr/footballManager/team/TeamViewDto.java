package com.github.patryktr.footballManager.team;

import com.github.patryktr.footballManager.user.User;

import java.util.List;


public record TeamViewDto(Long id, String name, int playersQuantity) {
}
