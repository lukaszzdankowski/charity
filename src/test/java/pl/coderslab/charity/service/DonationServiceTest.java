package pl.coderslab.charity.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonationServiceTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private DonationService donationService;

    @Test
    void countTotalQuantity_callsSumTotalQuantity() {
        //when
        donationService.countTotalQuantity();
        //then
        verify(donationRepository).sumTotalQuantity();
    }

    @Test
    void givenNoDonationsInDB_whenCallingCountTotalQuantity_shouldReturnZeroInt() {
        //given
        int expected = 0;
        when(donationRepository.sumTotalQuantity()).thenReturn(Optional.of(0));

        //when
        int actual = donationService.countTotalQuantity();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenSomeDonationsInDB_whenCallingCountTotalQuantity_shouldReturnInt() {
        //given
        int expected = 7;
        when(donationRepository.sumTotalQuantity()).thenReturn(Optional.of(7));

        //when
        int actual = donationService.countTotalQuantity();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void countTotalDonations_callsCount() {
        //when
        donationService.countTotalDonations();
        //then
        verify(donationRepository).count();
    }

    @Test
    void listAllDonations_callsFindAll() {
        //when
        donationService.listAllDonations();
        //then
        verify(donationRepository).findAll();
    }
}