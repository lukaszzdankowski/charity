package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public int countTotalQuantity() {
        return donationRepository.sumTotalQuantity().orElse(0);
    }

    public long countTotalDonations() {
        return donationRepository.count();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }
}
