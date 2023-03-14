package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailSenderService emailSenderService;
    private final TokenRepository tokenRepository;

    public void saveWithHash(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveNewUser(User user) throws MessagingException {
        user.getRoles().add(roleRepository.getById(2L));
        saveWithHash(user);
        emailSenderService.sendRegistration(user);
    }

    public String setUserActive(String tokenString) {
        Token token = tokenRepository.findById(tokenString).orElse(null);
        if (token == null) {
            return "guest/token-not-found";
        }
        User user = token.getUser();
        user.setActive(true);
        userRepository.save(user);
        tokenRepository.delete(token);
        return "guest/register-confirmed";
    }

    public String resetPasswordSend(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "guest/password-forgot-no-email";
        }
        emailSenderService.sendReminder(user);
        return "guest/password-forgot-sent";
    }

    public String resetPasswordReceive(String tokenString) {
        Token token = tokenRepository.findById(tokenString).orElse(null);
        if (token == null) {
            return "guest/token-not-found";
        }
        String email = token.getUser().getEmail();
        tokenRepository.delete(token);
        return email;
    }

    public void resetPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        user.setPassword(password);
        saveWithHash(user);
    }

}
