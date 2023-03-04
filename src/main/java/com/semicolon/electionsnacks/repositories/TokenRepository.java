package com.semicolon.electionsnacks.repositories;

import com.semicolon.electionsnacks.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Transactional
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    @Modifying
    @Query("UPDATE Token token" +
            " SET token.confirmedAt = ?1" +
            " WHERE token.token = ?2")
    void confirmedAt(LocalDateTime now, String token);

    void deleteTokenByExpiredAtBefore(LocalDateTime now);
}
