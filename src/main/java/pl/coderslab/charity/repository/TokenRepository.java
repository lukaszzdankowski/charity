package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;

public interface TokenRepository extends JpaRepository<Token, String> {
    void deleteAllByUser(User user);
}
