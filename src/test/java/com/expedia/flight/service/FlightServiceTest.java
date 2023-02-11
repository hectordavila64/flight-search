package com.expedia.flight.service;

import com.expedia.flight.entity.Flight;
import com.expedia.flight.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


@RunWith(SpringRunner.class)
@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    void shouldReturnAllFlightsInARangeOfPlusFiveHoursToTheGivenTime() {
        LocalTime givenTime = TimeUtil.parseTime("03:00AM");
        Flight[] expectedFlights = {
                new Flight("Air Canada 8099", TimeUtil.parseTime("7:30AM")),
                new Flight("Midnight", TimeUtil.parseTime("12:00AM"))
        };

        List<Flight> flights = flightService.filterByDeparture(givenTime);

        assertThat(flights.size(), is(2));
        assertThat(flights, containsInAnyOrder(expectedFlights));
    }

    @Test
    void shouldReturnAllFlightsInARangeOfMinusFiveHoursToTheGivenTime() {
        LocalTime givenTime = TimeUtil.parseTime("04:00PM");
        Flight[] expectedFlights = {
                new Flight("WestJet 6456", TimeUtil.parseTime("12:30PM")),
                new Flight("Delta 3833", TimeUtil.parseTime("3:00PM"))
        };

        List<Flight> flights = flightService.filterByDeparture(givenTime);

        assertThat(flights.size(), is(2));
        assertThat(flights, containsInAnyOrder(expectedFlights));
    }

    @Test
    void shouldReturnAllFlightsWithDepartureEqualsToTheGivenTimePlusFiveHours() {
        LocalTime givenTime = TimeUtil.parseTime("02:30AM");
        Flight[] expectedFlights = {
                new Flight("Air Canada 8099", TimeUtil.parseTime("7:30AM")),
                new Flight("Midnight", TimeUtil.parseTime("12:00AM"))
        };

        List<Flight> flights = flightService.filterByDeparture(givenTime);

        assertThat(flights.size(), is(2));
        assertThat(flights, containsInAnyOrder(expectedFlights));
    }

    @Test
    void shouldReturnAllFlightsWithDepartureEqualsToTheGivenTimeMinusFiveHours() {
        LocalTime givenTime = TimeUtil.parseTime("08:00PM");
        Flight expectedFlight =  new Flight("Delta 3833", TimeUtil.parseTime("3:00PM"));

        List<Flight> flights = flightService.filterByDeparture(givenTime);

        assertThat(flights.size(), is(1));
        assertThat(flights, containsInAnyOrder(expectedFlight));
    }

    @Test
    void shouldReturnAllFlightsWithDepartureEqualsToTheGivenTime() {
        LocalTime givenTime = TimeUtil.parseTime("03:00PM");
        Flight[] expectedFlights = {
                new Flight("WestJet 6456", TimeUtil.parseTime("12:30PM")),
                new Flight("Delta 3833", TimeUtil.parseTime("3:00PM")),
                new Flight("United Airline 6115", TimeUtil.parseTime("10:30AM"))
        };

        List<Flight> flights = flightService.filterByDeparture(givenTime);

        assertThat(flights.size(), is(3));
        assertThat(flights, containsInAnyOrder(expectedFlights));
    }
}
