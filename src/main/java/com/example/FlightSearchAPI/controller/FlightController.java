package com.example.FlightSearchAPI.controller;

import com.example.FlightSearchAPI.model.Flight;
import com.example.FlightSearchAPI.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/user/search")
    public ResponseEntity<?> searchFlights(@RequestParam("departureAirportId") Long departureAirportId,
                                           @RequestParam("arrivalAirportId") Long arrivalAirportId,
                                           @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
                                           @RequestParam(name = "returnDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate) {

        Object result = flightService.searchFlights(departureAirportId, arrivalAirportId, departureDate, returnDate);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

}
