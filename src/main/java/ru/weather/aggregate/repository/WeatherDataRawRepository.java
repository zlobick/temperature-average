package ru.weather.aggregate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weather.aggregate.entity.WeatherDataRaw;

import java.util.UUID;

public interface WeatherDataRawRepository extends JpaRepository<WeatherDataRaw, UUID> {
}
