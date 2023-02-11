package com.expedia.flight.util;


import com.expedia.flight.dao.FlightDAO;
import com.expedia.flight.exception.CSVFileNotFoundException;
import com.expedia.flight.exception.CSVMalformedException;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightInMemoryUtil {

    public static List<FlightDAO> getFromCSV(String csvPath, String csvSeparator) throws RuntimeException {
        List<FlightDAO> flights = new ArrayList<>();
        List<String> lines = getLinesFromCSV(csvPath);
        for (int i = 1; i < lines.size(); i++) {
            flights.add(getFlightFromLine(lines.get(i), csvSeparator));
        }
        return flights;
    }

    private static FlightDAO getFlightFromLine(String line, String csvSeparator) throws CSVMalformedException {
        String[] lineParts = line.split(csvSeparator);
        try {
            String flightName = lineParts[0];
            LocalTime departure = TimeUtil.parseTime(lineParts[1]);
            return new FlightDAO(0L, flightName, departure);
        } catch (Exception e) {
            throw new CSVMalformedException(e.getCause());
        }

    }

    private static List<String> getLinesFromCSV(String csvPath) throws CSVFileNotFoundException {
        try {
            URI uri = ClassLoader.getSystemResource(csvPath).toURI();
            return Files.readAllLines(Paths.get(uri));
        } catch (Exception e) {
            throw new CSVFileNotFoundException(e.getCause());
        }

    }
}
