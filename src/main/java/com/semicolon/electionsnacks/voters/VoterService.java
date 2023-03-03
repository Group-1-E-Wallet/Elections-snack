package com.semicolon.electionsnacks.voters;

import com.semicolon.electionsnacks.dtos.RegistrationRequest;
import com.semicolon.electionsnacks.models.Voter;

import java.util.Optional;

public interface VoterService {
    Voter registration(RegistrationRequest registrationRequest);
    Optional<Voter> findByEmailAddress(String emailAddress);
}
