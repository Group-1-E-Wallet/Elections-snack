package com.semicolon.electionsnacks.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Party party;
    private ElectionType electionType;
    private String lastName;
    private String firstName;
    private String LgaOfOrigin;
    private String state;
}
