package com.expedia.flight.repository;

import com.expedia.flight.dao.FlightDAO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FlightInMemoryRepository extends PagingAndSortingRepository<FlightDAO, String>,
        JpaSpecificationExecutor<FlightDAO> {

}
