package services.voters;

import dtos.request.EmailRequest;
import dtos.request.UpdatePasswordRequest;
import exceptions.GenericException;
import lombok.RequiredArgsConstructor;
import models.OTPToken;
import models.Voter;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.stereotype.Service;
import repositories.OtpTokenRepository;
import repositories.VoterRepository;
import services.EmailService;
import utils.PasswordEncoder;

import java.time.LocalDateTime;

import static utils.EmailUtils.sendEmail;


@Service
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService{
    private final VoterRepository voterRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Override
    public String forgotPassword(EmailRequest email) {
        Voter forgetVoter = voterRepository.findByEmailAddressIgnoreCase(email.getEmail())
                .orElseThrow(()->new GenericException("This email is not registered"));

        EmailRequest request = new EmailRequest();
        request.setEmail(email.getEmail());
        String token = generateOtpToken(request, forgetVoter);

        OTPToken otpToken = new OTPToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), forgetVoter
        );
        otpTokenRepository.save(otpToken);
        emailService.send(email.getEmail(), sendEmail(forgetVoter.getFirstName(), token));
        return "We have sent a new password link to your email. Please check.";

    }

    private String generateOtpToken(EmailRequest request, Voter forgetVoter) {
        return null;
    }

    @Override
    public String changePassword(UpdatePasswordRequest updatePasswordRequest) {
        String encodedOldPassword = passwordEncoder.encode(updatePasswordRequest.getOldPassword());
        Voter registeredVoter = voterRepository.findByEmailAddressIgnoreCase(updatePasswordRequest.getEmail())
                .orElseThrow(() -> new GenericException("email does not exist"));
        if (passwordEncoder.matches(encodedOldPassword, registeredVoter.getPassword))
            registeredVoter.setPassword(updatePasswordRequest.getNewPassword());
        voterRepository.save(registeredVoter);
        return  "Your password has been successfully changed";

    }
}
