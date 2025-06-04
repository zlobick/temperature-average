package ru.weather.aggregate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.weather.aggregate.dto.LastTemperatureDto;
import ru.weather.aggregate.service.WeatherDataService;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class TemperatureController {
    private final WeatherDataService weatherDataService;
    @GetMapping("/aggregate")
    public LastTemperatureDto averageTemperature() {
        return weatherDataService.getLastTemperature();
    }
}
