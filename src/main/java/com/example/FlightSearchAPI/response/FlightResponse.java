package com.example.FlightSearchAPI.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightResponse
{

    private String departureName;
    private String arrivalName;
    private LocalDateTime flightTime;
    private BigDecimal price;
}
