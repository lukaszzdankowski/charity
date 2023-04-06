package pl.coderslab.charity.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DonationRepositoryTest {

    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void givenNoDonationsInDB_whenCallingSumTotalQuantity_shouldReturnEmptyOptional() {
        //given
        Optional<Integer> expected = Optional.empty();

        //when
        Optional<Integer> actual = donationRepository.sumTotalQuantity();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenSomeDonationsInDB_whenCallingSumTotalQuantity_shouldReturnOptionalWithSum() {
        //given
        Donation donation1 = new Donation();
        donation1.setQuantity(2);
        donationRepository.save(donation1);

        Donation donation2 = new Donation();
        donation2.setQuantity(5);
        donationRepository.save(donation2);

        Optional<Integer> expected = Optional.of(7);

        //when
        Optional<Integer> actual = donationRepository.sumTotalQuantity();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenNoDonationsForUser_whenCallingFindAllByUser_shouldReturnEmptyList() {
        //given
        User user = new User();
        user.setEmail("test@test.com");
        userRepository.save(user);

        List<Donation> expected = new ArrayList<>();

        //when
        List<Donation> actual = donationRepository.findAllByUser(user);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenSomeDonationsForUser_whenCallingFindAllByUser_shouldReturnListOfDonations() {
        //given
        User user = new User();
        user.setEmail("test@test.com");
        userRepository.save(user);

        Donation donation1 = new Donation();
        donation1.setUser(user);
        donationRepository.save(donation1);

        Donation donation2 = new Donation();
        donation2.setUser(user);
        donationRepository.save(donation2);

        List<Donation> expected = new ArrayList<>();
        expected.add(donation1);
        expected.add(donation2);

        //when
        List<Donation> actual = donationRepository.findAllByUser(user);

        //then
        assertEquals(expected, actual);
    }
}