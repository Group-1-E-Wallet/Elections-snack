package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.models.Voter;

import java.util.Optional;

public interface VoterService {
    void enableVoter(String email);
    Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress);
}
