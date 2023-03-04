package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.exceptions.RegistrationException;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.exceptions.GenericException;
import com.semicolon.electionsnacks.models.OTPToken;
import com.semicolon.electionsnacks.repositories.OtpTokenRepository;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import com.semicolon.electionsnacks.services.EmailService;
import com.semicolon.electionsnacks.utils.PasswordEncoder;
import static com.semicolon.electionsnacks.utils.EmailUtils.sendEmail;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoterServiceImpl implements VoterService{
    private VoterRepository voterRepository;

    private OtpTokenRepository otpTokenRepository;

    private PasswordEncoder passwordEncoder;

    private EmailService emailService;

    @Override
    public void enableVoter(String email) {
        Voter foundEmail = voterRepository.
                findByEmailAddressIgnoreCase(email).
                orElseThrow(()->
                        new RegistrationException("Invalid email"));
        foundEmail.setIsVerified(true);
    }

    @Override
    public Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress) {
        return Optional.of(voterRepository.findByEmailAddressIgnoreCase(emailAddress).orElseThrow());
    }
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
