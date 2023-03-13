package com.semicolon.electionsnacks.dtos.UpdateRegistration;

import com.semicolon.electionsnacks.models.VoterDetails.Gender;
import lombok.Data;

@Data
public class UpdateRegistrationResponse {

    private Long id;
    private String lastName;
    private String firstName;
    private Gender gender;
    private String emailAddress;
    private Boolean isVerified;
//    private String dateOfBirth;
//    private String maritalStatus;
//    private String LgaOfOrigin;
//    private String state;
//    private String password;
//    private ElectionType electionType;
}
