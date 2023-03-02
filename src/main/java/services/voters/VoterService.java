package services.voters;

import dtos.request.EmailRequest;
import dtos.request.UpdatePasswordRequest;

public interface VoterService {
    String forgotPassword(EmailRequest emailRequest);
    String changePassword(UpdatePasswordRequest updatePasswordRequest);
}
