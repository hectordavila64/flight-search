package com.expedia.flight.util;


import com.expedia.flight.exception.InvalidTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeUtilTest {
    @Test
    void shouldReturnLocalTimeWhenHourHasNoPrePaddedZero() throws InvalidTimeException {
        String givenTime = "7:30AM";
        LocalTime expectedTime = LocalTime.of(7,30);

        LocalTime time = TimeUtil.parseTime(givenTime);

        assertEquals(expectedTime, time);
    }

    @Test
    void shouldReturnLocalTimeWhenAMPMMarkerIsInLowerCase() throws InvalidTimeException{
        String givenTime = "7:30am";
        LocalTime expectedTime = LocalTime.of(7,30);

        LocalTime time = TimeUtil.parseTime(givenTime);

        assertEquals(expectedTime, time);
    }

    @Test
    void shouldReturnLocalTimeWhenForPMTimes() throws InvalidTimeException{
        String givenTime = "7:30pm";
        LocalTime expectedTime = LocalTime.of(19,30);

        LocalTime time = TimeUtil.parseTime(givenTime);

        assertEquals(expectedTime, time);
    }
}
