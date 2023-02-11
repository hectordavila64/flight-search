package com.expedia.flight.model;

import com.expedia.flight.entity.Flight;
import com.expedia.flight.objectmother.FlightMother;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FlightTest {

    @Test
    void getFlight() {
        String expectedFlightName = FlightMother.generateRandomFlightValue();
        LocalTime departureTime = FlightMother.getCurrentLocalTime();
        Flight flight = new Flight(expectedFlightName, departureTime);

        String flightValue = flight.getFlight();

        assertEquals(expectedFlightName, flightValue);
    }

    @Test
    void getDeparture() {
        String flightName = FlightMother.generateRandomFlightValue();
        LocalTime expectedDepartureTime = FlightMother.getCurrentLocalTime();
        Flight flight = new Flight(flightName, expectedDepartureTime);

        LocalTime departureValue = flight.getDeparture();

        assertEquals(expectedDepartureTime, departureValue);
    }


}
