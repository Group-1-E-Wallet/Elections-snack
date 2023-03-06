package com.semicolon.electionsnacks.services.voters;
import com.semicolon.electionsnacks.dtos.request.ForgotPasswordRequest;
import com.semicolon.electionsnacks.dtos.request.ResetPasswordRequest;
import com.semicolon.electionsnacks.exceptions.RegistrationException;
import com.semicolon.electionsnacks.models.Token;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import com.semicolon.electionsnacks.services.email.EmailSender;
import com.semicolon.electionsnacks.services.registration.RegistrationService;
import com.semicolon.electionsnacks.services.token.TokenService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoterServiceImpl implements VoterService{
    private VoterRepository voterRepository;
    private TokenService tokenService;
//    private RegistrationService registrationService;
    private EmailSender emailSender;

    @Override
    public void enableVoter(String email) {
        Voter foundEmail = voterRepository.
                findByEmailAddressIgnoreCase(email).
                orElseThrow(()->
                        new RegistrationException("Invalid email"));
        foundEmail.setVerified(true);
    }

    @Override
    public Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress) {
        return voterRepository.findByEmailAddressIgnoreCase(emailAddress);
    }
    @Override
    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        var foundUser = voterRepository.findByEmailAddressIgnoreCase(forgotPasswordRequest.getEmailAddress());
        if (Objects.isNull(foundUser)) throw new RegistrationException("user does not exist");
        String token = generateToken(foundUser.get());
        emailSender.send(foundUser.get().getEmailAddress(), buildForgotPasswordEmail(foundUser.get().getLastName(), token));
        return token;
    }
    private String generateToken(Voter foundVoter) {
        SecureRandom random = new SecureRandom();

        String token = String.valueOf(1000 + random.nextInt(9999));
        Token confirmationToken = new Token(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                foundVoter
        );
        tokenService.saveConfirmationToken(confirmationToken);
        return confirmationToken.getToken();
    }

    private String buildForgotPasswordEmail (String lastName, String token){
        return "Here's the link to reset your password"
                + "                                      "
                + "                                        "
                + "<p>Hello \"" + lastName + "\",</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p>Token \"" + token + "\" Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
    }


    @Override
    public String changePassword(UpdatePasswordRequest updatePasswordRequest) {
        var user = voterRepository.findByEmailAddressIgnoreCase(updatePasswordRequest.getEmailAddress())
                .orElseThrow(() -> new RegistrationException("invalid details"));

        if (!BCrypt.checkpw(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RegistrationException("invalid details");
        }
        if (!updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getConfirmNewPassword()))
            throw new RegistrationException("password do not match");
        user.setPassword(hashPassword(updatePasswordRequest.getNewPassword()));
        voterRepository.save(user);
        return "Your password has been successfully changed";
    }

    @Override
    public void getUser(Voter voter) {
        voterRepository.save(voter);
    }



    @Override
    public String resetPassword (ResetPasswordRequest resetPasswordRequest){
        var tokenChecked = tokenService.getConfirmationToken(resetPasswordRequest.getToken())
                .orElseThrow(() -> new RegistrationException("Token does not exist"));

        if (tokenChecked.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RegistrationException("Token has expired");
        }
//        if (tokenChecked.getConfirmedAt() != null) {
//            throw new RegistrationException("Token has been used");
//        }
        tokenService.getConfirmationToken(tokenChecked.getToken());
        var user = voterRepository.findByEmailAddressIgnoreCase(resetPasswordRequest.getEmailAddress());
        user.get().setPassword(hashPassword(resetPasswordRequest.getPassword()));
        voterRepository.save(user.get());
        return "Your password successfully updated.";
    }
    private String hashPassword (String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}