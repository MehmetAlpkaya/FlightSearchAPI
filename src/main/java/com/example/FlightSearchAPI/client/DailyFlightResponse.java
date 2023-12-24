package com.example.FlightSearchAPI.client;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DailyFlightResponse
{
    private String departureAirportName;
    private String arrivalAirportName;
    private LocalDateTime departureTime;
    private BigDecimal price;
}
