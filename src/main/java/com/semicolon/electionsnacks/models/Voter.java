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
@Entity
public class Voter {
    public String getPassword;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String Gender;
    private String emailAddress;
    private String dateOfBirth;
    private String maritalStatus;
    private String LgaOfOrigin;
    private String state;
    private String residentialAddress;
    private Boolean isVerified;
    private String password;
    private ElectionType electionType;
    private String emailAddress;
}
