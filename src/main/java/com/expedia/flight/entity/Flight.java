package com.expedia.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Objects;


@Getter
@AllArgsConstructor
public class Flight {

    String flight;
    LocalTime departure;


    @Override
    public int hashCode() {
        return Objects.hash(flight, departure);
    }

    @Override
    public boolean equals(Object flight){
        if(!(flight instanceof Flight)) return false;
        Flight o = (Flight) flight;
        return this.flight.equals(o.flight) && this.departure.equals(o.departure);
    }
}
