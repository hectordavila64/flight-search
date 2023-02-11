package com.expedia.flight.controller;

import com.expedia.flight.entity.Flight;
import com.expedia.flight.exception.InvalidTimeException;
import com.expedia.flight.service.FlightService;
import com.expedia.flight.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
public class FlightController {


    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("api/flights")
    public ResponseEntity<List<Flight>> get(@RequestParam(value = "departure") String departure) throws InvalidTimeException {
        LocalTime departureTime = TimeUtil.parseTime(departure);
        List<Flight> flights = flightService.filterByDeparture(departureTime);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
