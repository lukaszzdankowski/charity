package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
