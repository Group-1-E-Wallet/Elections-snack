package com.semicolon.electionsnacks.repositories;

import com.semicolon.electionsnacks.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmailAddressIgnoreCase(String email);
}
