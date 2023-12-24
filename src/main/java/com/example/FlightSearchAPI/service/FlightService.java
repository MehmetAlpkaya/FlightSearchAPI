package com.example.FlightSearchAPI.service;

import com.example.FlightSearchAPI.client.DailyFlightResponse;
import com.example.FlightSearchAPI.client.FlightInformationClient;
import com.example.FlightSearchAPI.model.Airport;
import com.example.FlightSearchAPI.model.Flight;
import com.example.FlightSearchAPI.repository.AirportRepository;
import com.example.FlightSearchAPI.repository.FlightRepository;
import com.example.FlightSearchAPI.response.FlightResponse;
import com.example.FlightSearchAPI.response.SearchApiResponse;
import com.zaxxer.hikari.util.FastList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final FlightInformationClient flightInformationClient;
    public Object searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDate, LocalDateTime returnDate) {

        SearchApiResponse searchApiResponse=new SearchApiResponse();
        List<Flight> departureFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(departureAirportId, arrivalAirportId, departureDate);
        List<FlightResponse> mappedDepartureFlights = new ArrayList<>();
        for (Flight flight : departureFlights) {
            mappedDepartureFlights.add(convertToFlightResponse(flight));
        }

        searchApiResponse.setDepartureFlights(mappedDepartureFlights);
        if(Objects.nonNull(returnDate))
        {

            List<Flight> arrivalFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(arrivalAirportId, departureAirportId, returnDate);
            List<FlightResponse> mappedarrivalFlights = new ArrayList<>();
            for (Flight flight : arrivalFlights) {
                mappedarrivalFlights.add(convertToFlightResponse(flight));
            }
            searchApiResponse.setArrivalFlights(mappedarrivalFlights);
        }
        return searchApiResponse;
    }

    public void saveFlightFromClient() {

        List<DailyFlightResponse> flightInfo = flightInformationClient.getFlightInfo();
        List<Flight> flightList=new ArrayList<>();
        for(DailyFlightResponse info: flightInfo) {

            Airport departureAirport = airportRepository.findAirportByCity(info.getDepartureAirportName());
            Airport arrivalAirport = airportRepository.findAirportByCity(info.getArrivalAirportName());
            Flight flight = convertToFlight(departureAirport, arrivalAirport, info);
            flightList.add(flight);
        }
        flightRepository.saveAll(flightList);
    }
    public Flight convertToFlight(Airport departure, Airport arrival, DailyFlightResponse dailyFlightResponse) {

        Flight flight=new Flight();
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        flight.setPrice(dailyFlightResponse.getPrice());
        flight.setDepartureDateTime(dailyFlightResponse.getDepartureTime());
        return flight;
    }
    private FlightResponse convertToFlightResponse(Flight flight) {
        FlightResponse response = new FlightResponse();
        response.setDepartureName(flight.getDepartureAirport().getCity());
        response.setArrivalName(flight.getArrivalAirport().getCity());
        response.setFlightTime(flight.getDepartureDateTime());
        response.setPrice(flight.getPrice());
        return response;
    }

}
