package com.example.FlightSearchAPI.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchApiResponse
{
private List<FlightResponse> departureFlights;
private List<FlightResponse> arrivalFlights;
}
