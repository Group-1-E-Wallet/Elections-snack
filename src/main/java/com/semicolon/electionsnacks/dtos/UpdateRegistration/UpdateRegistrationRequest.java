package com.semicolon.electionsnacks.dtos.UpdateRegistration;

import com.semicolon.electionsnacks.models.VoterDetails.Gender;
import com.semicolon.electionsnacks.models.VoterDetails.LGAOrigin;
import com.semicolon.electionsnacks.models.VoterDetails.MaritalStatus;
import com.semicolon.electionsnacks.models.VoterDetails.State;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateRegistrationRequest {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private LGAOrigin LgaOfOrigin;
    private State state;
    private String residentialAddress;
    private Boolean isVerified;
    private LocalDate dateOfBirth;
}
