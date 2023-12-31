package com.github.patryktr.footballManager.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
