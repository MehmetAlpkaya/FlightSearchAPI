package com.example.FlightSearchAPI.repository;

import com.example.FlightSearchAPI.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {

   Airport findAirportByCity(String city);
}
