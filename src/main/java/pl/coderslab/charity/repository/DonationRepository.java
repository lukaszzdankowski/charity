package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("select sum(d.quantity) from Donation d")
    public Integer sumTotalQuantity();
}
