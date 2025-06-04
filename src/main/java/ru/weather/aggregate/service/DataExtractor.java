package ru.weather.aggregate.service;

import java.time.LocalDateTime;

public interface DataExtractor {
    /**
     * Извлекает и сохраняет данные в базе
     * @param localDateTime
     */
    void extractData(LocalDateTime localDateTime);
}
