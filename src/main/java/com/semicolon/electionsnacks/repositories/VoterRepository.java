package com.semicolon.electionsnacks.repositories;
import com.semicolon.electionsnacks.models.Voter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress);
    Optional<Voter> findById(Long id);
    @Modifying
    @Query("UPDATE Voter voter SET voter.isVerified = ?1 WHERE voter.emailAddress = ?2")
    void enableVoter(String emailAddress);
}
