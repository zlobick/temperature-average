package ru.weather.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "normalized_weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "source_id")
    private Integer sourceId;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "humidity")
    private double humidity;
    @Column(name = "created_at")
    private LocalDateTime timestamp;
}
