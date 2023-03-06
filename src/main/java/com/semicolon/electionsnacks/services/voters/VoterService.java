package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.dtos.request.ForgotPasswordRequest;
import com.semicolon.electionsnacks.dtos.request.ResetPasswordRequest;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;

import java.util.Optional;

public interface VoterService {
    void enableVoter(String email);
    Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress);

    String forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    String changePassword(UpdatePasswordRequest updatePasswordRequest);
    void getUser(Voter voter);
    String resetPassword (ResetPasswordRequest resetPasswordRequest);
}
