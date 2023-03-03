package com.semicolon.electionsnacks.repositories;

import com.semicolon.electionsnacks.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmailAddressIgnoreCase(String email);
}
