package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OTPToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expiredAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "voter_id", referencedColumnName="id")
    private  Voter voter;

    public OTPToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, Voter voter) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.voter = voter;
    }
}
