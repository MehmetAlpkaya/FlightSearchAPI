package com.example.FlightSearchAPI.repository;

import com.example.FlightSearchAPI.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(
            Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDate);


}
