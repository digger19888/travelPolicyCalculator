package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {


    public TravelCalculatePremiumRequest request() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jimmy");
        request.setPersonLastName("Dory");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        return request;
    }

    public TravelCalculatePremiumResponse response() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        return service.calculatePremium(request());
    }

    @Test
    public void correctFirstName() {
        assertEquals(response().getPersonFirstName(), request().getPersonFirstName());

    }

    @Test
    public void correctLastName() {
        assertEquals(response().getPersonLastName(), request().getPersonLastName());

    }

    @Test
    public void correctDateFrom() {
        assertEquals(response().getAgreementDateFrom(), request().getAgreementDateFrom());

    }

    @Test
    public void correctDateTo() {
        assertEquals(response().getAgreementDateTo(), request().getAgreementDateTo());

    }
    @Test
    public void shouldPopulateAgreementPrice() {
        assertNotNull(response().getAgreementPrice());
    }

}