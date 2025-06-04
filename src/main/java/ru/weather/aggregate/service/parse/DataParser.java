package ru.weather.aggregate.service.parse;

import ru.weather.aggregate.exceptions.ParseException;

public interface DataParser {
    /**
     * Разбирает полученные данные от источника, извлекает температуру и влажность.
     * @param fetched данные, полученные от источника
     * @return температура и влажность
     * @throws ParseException исключение, при невозможности разобрать данные
     */
    TemperatureData parseTemperature(String fetched) throws ParseException;
    record TemperatureData(Double temperature, Double humidity) {}
}
