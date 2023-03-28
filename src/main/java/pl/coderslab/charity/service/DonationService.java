package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.enumeration.DonationStatus;
import pl.coderslab.charity.repository.DonationRepository;

import javax.mail.MessagingException;
import java.util.List;

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
        donation.setUser(user);
        donation.setStatus(DonationStatus.REPORTED);
        donationRepository.save(donation);
        emailService.sendSummary(user, donation);
    }

    public List<Donation> donationListForUser(User user) {
        return donationRepository.findAllByUser(user);
    }

    public List<Donation> listAllDonations() {
        return donationRepository.findAll();
    }

    public void setDonationAsDelivered(String donationId) {
        Donation donation = donationRepository.findById(Long.parseLong(donationId)).orElseThrow(RuntimeException::new);
        donation.setStatus(DonationStatus.DELIVERED);
        donationRepository.save(donation);
    }

    public void setDonationAsArchived(String donationId) {
        Donation donation = donationRepository.findById(Long.parseLong(donationId)).orElseThrow(RuntimeException::new);
        donation.setStatus(DonationStatus.ARCHIVED);
        donationRepository.save(donation);
    }
}
