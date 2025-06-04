package ru.weather.aggregate.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.weather.aggregate.dto.LastTemperatureDto;
import ru.weather.aggregate.entity.WeatherData;
import ru.weather.aggregate.entity.WeatherDataRaw;
import ru.weather.aggregate.repository.WeatherDataRawRepository;
import ru.weather.aggregate.repository.WeatherDataRepository;

import java.util.Optional;

/**
 * TODO сущность не должна передаваться за границы транзакционного метода во избежание LazyInicalizationException
 * Данное истолнение - для экономии времени в рамках тестового задания.
 */
@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataRawRepository weatherDataRawRepository;

    @Override
    @Transactional
    public void save(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }

    @Override
    @Transactional
    public void save(WeatherDataRaw weatherData) {
        weatherDataRawRepository.save(weatherData);
    }

    @Override
    @Transactional
    public LastTemperatureDto getLastTemperature() {
        return Optional.ofNullable(weatherDataRepository.getMaxCreatedAt())
                .map(weatherDataRepository::getAvg)
                .map(projection -> new LastTemperatureDto(projection.getTemperature(), projection.getHumidity()))
                .orElse(null);
    }
}
