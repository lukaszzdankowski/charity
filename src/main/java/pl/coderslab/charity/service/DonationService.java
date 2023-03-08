package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public int countTotalQuantity() {
        if (donationRepository.sumTotalQuantity() == null) {
            return 0;
        }
        return donationRepository.sumTotalQuantity();
    }

    public long countTotalDonations() {
        return donationRepository.count();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }
}
