package ru.weather.aggregate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.weather.aggregate.entity.WeatherData;

import java.time.LocalDateTime;
import java.util.UUID;

public interface WeatherDataRepository extends JpaRepository<WeatherData, UUID> {
    @Query("select avg(temperature) as temperature, avg (humidity) as humidity from WeatherData where timestamp = :timestamp")
    Projection getAvg(LocalDateTime timestamp);
    @Query("select max(timestamp) from WeatherData")
    LocalDateTime getMaxCreatedAt();

    interface Projection {
        Double getTemperature();
        Double getHumidity();
    }
}
