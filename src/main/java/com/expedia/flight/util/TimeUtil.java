package com.expedia.flight.util;


import com.expedia.flight.exception.InvalidTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static LocalTime parseTime(String time) throws InvalidTimeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
            return LocalTime.parse(format(time), dateTimeFormatter);
        } catch (Exception e) {
            throw new InvalidTimeException(e.getCause());
        }
    }

    private static String format(String time) {
        return ("0000000" + time).substring(time.length()).toUpperCase();
    }
}
