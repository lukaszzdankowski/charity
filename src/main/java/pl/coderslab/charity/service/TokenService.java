package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);

        String hashCode = UUID.randomUUID().toString();
        while (tokenRepository.existsById(hashCode)) {
            hashCode = UUID.randomUUID().toString();
        }
        token.setHashCode(hashCode);

        tokenRepository.save(token);
        return token;
    }

    public boolean checkIfTokenValid(Token token) {
        Instant tokenTime = token.getCreated().toInstant(ZoneOffset.UTC);
        Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        if (Duration.between(tokenTime, now).getSeconds() < (30 * 60)) {
            return true;
        }
        return false;
    }

    public String retrieveEmailAndDeleteToken(String tokenString) {
        Token token = tokenRepository.findById(tokenString).orElseThrow(RuntimeException::new);
        String email = token.getUser().getEmail();
        tokenRepository.delete(token);
        return email;
    }
}
