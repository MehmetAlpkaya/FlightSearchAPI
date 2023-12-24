package com.example.FlightSearchAPI.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@FeignClient(value = "flight-info-service", url = "http://localhost:8081/")
public interface FlightInformationClient
{
    @GetMapping("/get-daily-flight")
    List<DailyFlightResponse> getFlightInfo();
}
