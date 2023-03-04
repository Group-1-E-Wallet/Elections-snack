package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;

import java.util.Optional;

public interface VoterService {
    void enableVoter(String email);
    Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress);

    String forgotPassword(EmailAddressRequest emailAddressRequest);
    String changePassword(UpdatePasswordRequest updatePasswordRequest);
}
