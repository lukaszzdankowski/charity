package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
}
