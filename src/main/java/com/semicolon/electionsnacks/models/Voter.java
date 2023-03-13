package com.semicolon.electionsnacks.models;

import com.semicolon.electionsnacks.models.VoterDetails.Gender;
import com.semicolon.electionsnacks.models.VoterDetails.LGAOrigin;
import com.semicolon.electionsnacks.models.VoterDetails.MaritalStatus;
import com.semicolon.electionsnacks.models.VoterDetails.State;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


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
    private Gender gender;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private MaritalStatus maritalStatus;
    private LGAOrigin LgaOfOrigin;
    private String password;
    private State state;
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
