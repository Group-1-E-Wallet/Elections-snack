package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;


public interface VoterService {
    String forgotPassword(EmailAddressRequest emailAddressRequest);
    String changePassword(UpdatePasswordRequest updatePasswordRequest);
}
