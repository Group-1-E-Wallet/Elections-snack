package com.semicolon.electionsnacks.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
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
    private String password;
    private String state;
    private String residentialAddress;
    private boolean isVerified = false;
    private ElectionType electionType;


    public Voter(String emailAddress, String firstName, String lastName, String password) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


}
