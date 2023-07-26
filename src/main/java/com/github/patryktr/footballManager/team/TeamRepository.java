package com.github.patryktr.footballManager.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Team deleteTeamById(long id);
    Optional<Team> findByName(String name);
}
