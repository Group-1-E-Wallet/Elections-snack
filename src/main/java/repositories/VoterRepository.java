package repositories;

import jakarta.transaction.Transactional;
import models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
@Transactional
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress);
    Optional<Voter> findById(Long id);
    @Modifying
    @Query("UPDATE Voter voter SET voter.status = ?1 WHERE voter.emailAddress = ?2")
    void enableVoter(Status verified, String emailAddress);
}
