package com.semicolon.electionsnacks.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String password;
    private String gender;
    private String dateOfBirth;
    private String maritalStatus;
    private String LgaOfOrigin;
    private String state;
    private String residentialAddress;
    private Boolean isVerified;
}
