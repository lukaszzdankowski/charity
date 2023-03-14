package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import javax.mail.MessagingException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final EmailSenderService emailSenderService;

    public int countTotalQuantity() {
        return donationRepository.sumTotalQuantity().orElse(0);
    }

    public long countTotalDonations() {
        return donationRepository.count();
    }

    public void saveAndSendMessage(Donation donation, Principal principal) throws MessagingException {
        donationRepository.save(donation);
        emailSenderService.sendSummary(principal, donation);
    }
}
