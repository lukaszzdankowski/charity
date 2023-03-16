package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final EmailService emailService;

    public int countTotalQuantity() {
        return donationRepository.sumTotalQuantity().orElse(0);
    }

    public long countTotalDonations() {
        return donationRepository.count();
    }

    public void saveAndSendMessage(User user, Donation donation) throws MessagingException {
        donationRepository.save(donation);
        emailService.sendSummary(user, donation);
    }
}
