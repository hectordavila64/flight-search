package com.expedia.flight.objectmother;

import com.expedia.flight.entity.Flight;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightMother {

    public static List<Flight> generate(int amount) {
        List<Flight> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(generate());
        }
        return list;
    }

    public static List<Flight> generateWithDepartureBefore(int amount, LocalTime time) {
        List<Flight> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(generateWithDepartureBefore(time));
        }
        return list;
    }


    public static String generateRandomFlightValue() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    private static Flight generate() {
        String flightName = generateRandomFlightValue();
        LocalTime departure = getCurrentLocalTime();
        return new Flight(flightName, departure);
    }

    private static Flight generateWithDepartureBefore(LocalTime time) {
        String flightName = generateRandomFlightValue();
        LocalTime departure = generateRandomTimeBefore(time);
        return new Flight(flightName, departure);
    }

    private static LocalTime generateRandomTimeBefore(LocalTime time){
        int timeHour = time.getHour();
        int randomHour = generateRandom(0, timeHour - 1);
        return LocalTime.of(timeHour - randomHour, time.getMinute());
    }

    private static int generateRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
