package ru.weather.aggregate.service;

import org.springframework.stereotype.Component;
import ru.weather.aggregate.dto.LastTemperatureDto;
import ru.weather.aggregate.entity.WeatherData;
import ru.weather.aggregate.entity.WeatherDataRaw;

@Component
public interface WeatherDataService {
    void save(WeatherData weatherData);
    void save(WeatherDataRaw weatherData);
    LastTemperatureDto getLastTemperature();
}
