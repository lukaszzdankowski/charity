package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
