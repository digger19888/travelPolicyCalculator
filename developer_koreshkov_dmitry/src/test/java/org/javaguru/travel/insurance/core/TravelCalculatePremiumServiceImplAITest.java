package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAITest {
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TravelCalculatePremiumServiceImpl();
    }

    @Test
    void calculatePremium_shouldReturnResponseWithSameFieldsAsRequest() {
        // Given
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        Date fromDate = new Date(2023, Calendar.FEBRUARY, 1);
        Date toDate = new Date(2023, Calendar.DECEMBER, 31);
        request.setAgreementDateFrom(fromDate);
        request.setAgreementDateTo(toDate);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertNotNull(response, "Response should not be null");
        assertEquals("John", response.getPersonFirstName(), "First name should match");
        assertEquals("Doe", response.getPersonLastName(), "Last name should match");
        assertEquals(fromDate, response.getAgreementDateFrom(), "Start date should match");
        assertEquals(toDate, response.getAgreementDateTo(), "End date should match");
    }

    @Test
    void calculatePremium_shouldHandleNullRequest() {
        // Given
        TravelCalculatePremiumRequest request = null;

        // When & Then
        assertThrows(NullPointerException.class,
                () -> service.calculatePremium(request),
                "Should throw NullPointerException for null request");
    }

    @Test
    void calculatePremium_shouldHandleNullFieldsInRequest() {
        // Given
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);
        request.setPersonLastName(null);
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(null);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertNotNull(response, "Response should not be null even for null fields");
        assertNull(response.getPersonFirstName(), "First name should be null");
        assertNull(response.getPersonLastName(), "Last name should be null");
        assertNull(response.getAgreementDateFrom(), "Start date should be null");
        assertNull(response.getAgreementDateTo(), "End date should be null");
    }

    @Test
    void calculatePremium_shouldHandleEmptyNames() {
        // Given
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName(" ");
        Date fromDate = new Date(); // current date
        Date toDate = new Date(fromDate.getTime() + 10 * 24 * 60 * 60 * 1000); // 10 days later
        request.setAgreementDateFrom(fromDate);
        request.setAgreementDateTo(toDate);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals("", response.getPersonFirstName(), "Empty first name should be preserved");
        assertEquals(" ", response.getPersonLastName(), "Whitespace last name should be preserved");
    }
}
