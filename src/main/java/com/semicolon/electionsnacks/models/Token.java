package com.semicolon.electionsnacks.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String token;
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private LocalDateTime expiredAt;
    @NonNull
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "voter_id",
            referencedColumnName = "id")
    private Voter voter;

    public Token(String token, LocalDateTime createdAt, LocalDateTime expiredAt, Voter voter) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.voter = voter;
    }
}
