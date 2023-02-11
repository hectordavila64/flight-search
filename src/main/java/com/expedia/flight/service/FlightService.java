package com.expedia.flight.service;

import com.expedia.flight.dao.FlightDAO;
import com.expedia.flight.entity.Flight;
import com.expedia.flight.exception.InvalidTimeException;
import com.expedia.flight.mapper.FlightMapper;
import com.expedia.flight.repository.FlightInMemoryRepository;
import com.expedia.flight.specification.FlightSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    @Value("${flight.filter.departure.threshold}")
    private Integer hourThreshold;
    @Value("${flight.filter.departure.maxHourOfDay}")
    private Integer maxHourOfDay;

    private FlightInMemoryRepository flightInMemoryRepository;

    @Autowired
    public FlightService(FlightInMemoryRepository flightInMemoryRepository) {
        this.flightInMemoryRepository = flightInMemoryRepository;
    }

    public List<Flight> filterByDeparture(LocalTime departure) throws InvalidTimeException {
        Specification<FlightDAO> filter = generateDepartureFilter(departure);
        List<FlightDAO> flights = flightInMemoryRepository.findAll(filter);
        return FlightMapper.convert(flights);
    }

    private Specification<FlightDAO> generateDepartureFilter(LocalTime departure) {
        LocalTime max = departure.plusHours(Math.min(hourThreshold, maxHourOfDay - departure.getHour()));
        max = max.getHour() == LocalTime.MIDNIGHT.getHour() ? LocalTime.MAX : max;
        LocalTime min = departure.minusHours(Math.min(hourThreshold, departure.getHour()));
        min = min.getHour() == LocalTime.MIDNIGHT.getHour() ? LocalTime.MIN : min;
        return FlightSpecification.filter(min, max);
    }
}
