package com.example.FlightSearchAPI.scheduler;

import com.example.FlightSearchAPI.service.FlightService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class FlightDataScheduler
{

    private final FlightService flightService;
    public FlightDataScheduler( FlightService flightService) {

        this.flightService = flightService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Her gün 00:00'da çalışır
    public void fetchAndSaveAirpotData() {
       flightService.saveFlightFromClient();
    }



}
