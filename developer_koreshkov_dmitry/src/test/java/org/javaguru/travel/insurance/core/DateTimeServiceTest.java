package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldDaysBetweenBeZero() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(0L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBeOneForConsecutiveDays() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("02.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(1L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBeNegativeWhenSecondDateEarlier() {
        Date date1 = createDate("10.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(-9L, daysBetween);
    }

    @Test
    public void shouldCorrectlyCalculateDaysInLeapYear() {
        Date date1 = createDate("28.02.2020");
        Date date2 = createDate("01.03.2020");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(2L, daysBetween);
    }

    @Test
    public void shouldCorrectlyCalculateDaysBetweenDistantDates() {
        Date date1 = createDate("01.01.2000");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(8400L, daysBetween);
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse date: " + dateString, e);
        }
    }
}
