package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.enumeration.DonationStatus;
import pl.coderslab.charity.repository.DonationRepository;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Donation> listAllDonations() {
        return donationRepository.findAll();
    }

    public List<Donation> donationListForUser(User user) {
        return donationRepository.findAllByUser(user).stream()
                .filter(donation -> !donation.getStatus().equals(DonationStatus.ARCHIVED))
                .collect(Collectors.toList());
    }

    public List<Donation> donationListForCourier() {
        return donationRepository.findAll().stream()
                .filter(donation -> !donation.getStatus().equals(DonationStatus.ARCHIVED))
                .collect(Collectors.toList());
    }

    public void setDonationAsReceived(String donationId) {
        Donation donation = donationRepository.findById(Long.parseLong(donationId)).orElseThrow(RuntimeException::new);
        donation.setStatus(DonationStatus.RECEIVED);
        donationRepository.save(donation);
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
