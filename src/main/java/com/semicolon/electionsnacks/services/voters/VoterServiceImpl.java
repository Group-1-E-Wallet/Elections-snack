package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.exceptions.GenericException;
import com.semicolon.electionsnacks.models.OTPToken;
import com.semicolon.electionsnacks.models.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semicolon.electionsnacks.repositories.OtpTokenRepository;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import com.semicolon.electionsnacks.services.EmailService;
import com.semicolon.electionsnacks.utils.PasswordEncoder;

import java.time.LocalDateTime;

import static com.semicolon.electionsnacks.utils.EmailUtils.sendEmail;



@Service
public class VoterServiceImpl implements VoterService{
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private OtpTokenRepository otpTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;


    @Override
    public String forgotPassword(EmailAddressRequest email) {
        Voter forgetVoter = voterRepository.findByEmailAddressIgnoreCase(email.getEmailAddress())
                .orElseThrow(()->new GenericException("This email is not registered"));

        EmailAddressRequest request = new EmailAddressRequest();
        request.setEmailAddress(email.getEmailAddress());
        String token = generateOtpToken(request, forgetVoter);

        OTPToken otpToken = new OTPToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), forgetVoter
        );
        otpTokenRepository.save(otpToken);
        emailService.send(email.getEmailAddress(), sendEmail(forgetVoter.getFirstName(), token));
        return "We have sent a new password link to your email. Please check.";

    }

    private String generateOtpToken(EmailAddressRequest request, Voter forgetVoter) {
        return null;
    }

    @Override
    public String changePassword(UpdatePasswordRequest updatePasswordRequest) {
        String encodedOldPassword = passwordEncoder.encode(updatePasswordRequest.getOldPassword());
        Voter registeredVoter = voterRepository.findByEmailAddressIgnoreCase(updatePasswordRequest.getEmailAddress())
                .orElseThrow(() -> new GenericException("email does not exist"));
        if (passwordEncoder.matches(encodedOldPassword, registeredVoter.getPassword))
            registeredVoter.setPassword(updatePasswordRequest.getNewPassword());
        voterRepository.save(registeredVoter);
        return  "Your password has been successfully changed";

    }
}
