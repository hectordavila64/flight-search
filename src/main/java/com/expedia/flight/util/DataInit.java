package com.expedia.flight.util;


import com.expedia.flight.dao.FlightDAO;
import com.expedia.flight.repository.FlightInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataInit implements ApplicationRunner {

    @Value("${repository.csv.path}")
    private  String csvPath;
    @Value("${repository.csv.separator}")
    private  String csvSeparator;

    private FlightInMemoryRepository flightInMemoryRepository;

    @Autowired
    public DataInit(FlightInMemoryRepository flightInMemoryRepository){
        this.flightInMemoryRepository = flightInMemoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<FlightDAO> flights = FlightInMemoryUtil.getFromCSV(csvPath, csvSeparator);
        if(flightInMemoryRepository.count() == 0){
            for (FlightDAO flight : flights) {
                flightInMemoryRepository.save(flight);
            }
        }
    }
}
