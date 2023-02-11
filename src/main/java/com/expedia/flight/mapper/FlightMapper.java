package com.expedia.flight.mapper;


import com.expedia.flight.dao.FlightDAO;
import com.expedia.flight.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightMapper {

    public static Flight convert(FlightDAO flight) {
        return new Flight(flight.getName(), flight.getDeparture());
    }

    public static List<Flight> convert(List<FlightDAO> flights) {
        return flights.stream().map(FlightMapper::convert).collect(Collectors.toList());
    }
}
