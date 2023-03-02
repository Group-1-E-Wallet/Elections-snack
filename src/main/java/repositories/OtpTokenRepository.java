package repositories;

import models.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpTokenRepository extends JpaRepository<OTPToken, Long> {
    Optional<OTPToken> findByToken(String token);
    @Modifying
    @Query("UPDATE OTPToken otpToken " +
            "SET otpToken.verifiedAt= ?1 " +
            "WHERE otpToken.token = ?2")
    void setVerifiedAt(LocalDateTime verified, String token);

    OTPToken findByVoterId(Long voterId);
    @Modifying
    @Query("UPDATE OTPToken otpToken SET otpToken = ?1 WHERE OTPToken.voter.id = ?2 ")
    void saveExistingOTP(OTPToken otpToken, Long voterId);
}
