package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token generateToken(User user, String purpose) {
        Token token = new Token();
        token.setUser(user);//czy tu całego USERA czy mozna id
        token.setPurpose(purpose);

        String hashCode = UUID.randomUUID().toString();
        while (tokenRepository.existsById(hashCode)) {
            hashCode = UUID.randomUUID().toString();
        }
        token.setHashCode(hashCode);

        tokenRepository.save(token);
        return token;
    }
}
