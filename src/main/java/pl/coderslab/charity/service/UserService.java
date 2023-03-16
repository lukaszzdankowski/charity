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
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void saveWithHash(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveNewUser(User user) throws MessagingException {
        user.getRoles().add(roleRepository.getById(2L));
        saveWithHash(user);
        emailService.sendRegistration(user);
    }

    public int setUserActive(String tokenString) {
        Token token = tokenRepository.findById(tokenString).orElse(null);
        if (token == null) {
            return 0;
        }
        User user = token.getUser();
        if (!tokenService.checkIfTokenValid(token)) {
            tokenRepository.delete(token);
            userRepository.removeRolesFromUser(user.getId());
            userRepository.delete(user);
            return 1;
        }
        user.setActive(true);
        userRepository.save(user);
        tokenRepository.delete(token);
        return 2;
    }

    public boolean resetPasswordSend(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }
        emailService.sendReminder(user);
        return true;
    }

    public int resetPasswordReceive(String tokenString) {
        Token token = tokenRepository.findById(tokenString).orElse(null);
        if (token == null) {
            return 0;
        }
        if (!tokenService.checkIfTokenValid(token)) {
            tokenRepository.delete(token);
            return 1;
        }
        return 2;
    }

    public void resetPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        user.setPassword(password);
        saveWithHash(user);
    }
}
