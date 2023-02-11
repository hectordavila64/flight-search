package com.expedia.flight.specification;


import com.expedia.flight.dao.FlightDAO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.time.LocalTime;

public class FlightSpecification  {

    private static final String DEPARTURE_FILED_NAME = "departure";

    public static Specification<FlightDAO> departureGreaterThanOrEqualTo(LocalTime time) {
        return (root, query, cb) -> {
            Expression<LocalTime> departureField = root.get(DEPARTURE_FILED_NAME);
            return cb.greaterThanOrEqualTo(departureField, time);
        };
    }

    public static Specification<FlightDAO> departureLessThanOrEqualTo(LocalTime time) {
        return (root, query, cb) -> {
            Expression<LocalTime> departureField = root.get(DEPARTURE_FILED_NAME);
            return  cb.lessThanOrEqualTo(departureField,time);
        };
    }

    public static Specification<FlightDAO> filter(LocalTime min, LocalTime max) {
        return Specification.where(departureGreaterThanOrEqualTo(min))
                 .and(departureLessThanOrEqualTo(max));
    }
}
