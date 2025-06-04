package ru.weather.aggregate.service;

import lombok.RequiredArgsConstructor;
import ru.weather.aggregate.entity.WeatherData;
import ru.weather.aggregate.entity.WeatherDataRaw;
import ru.weather.aggregate.exceptions.FetchException;
import ru.weather.aggregate.exceptions.ParseException;
import ru.weather.aggregate.service.fetch.DataFetcher;
import ru.weather.aggregate.service.parse.DataParser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DataExtractorImpl implements DataExtractor {
    private final Integer setSourceId;
    private final DataFetcher dataFetcher;
    private final DataParser dataParser;
    private final WeatherDataService weatherDataService;
    @Override
    public void extractData(LocalDateTime localDateTime) {
        try {
            String fetched = dataFetcher.fetch(localDateTime);
            WeatherDataRaw toSaveFetched = new WeatherDataRaw();
            toSaveFetched.setPayload(fetched);
            toSaveFetched.setTimestamp(localDateTime);
            toSaveFetched.setSourceId(setSourceId);

            // сохранение должно происходить в отдельной транзакции,
            // чтобы при ошибке парсинга были сохранены грязные данные
            // отдельный сервис - для неявного управления транзакциями
            // (можно и вручную через TransactionManager)
            weatherDataService.save(toSaveFetched);

            DataParser.TemperatureData parsed = dataParser.parseTemperature(fetched);
            WeatherData toSaveParsed = new WeatherData();
            toSaveParsed.setSourceId(setSourceId);
            toSaveParsed.setTimestamp(localDateTime);
            toSaveParsed.setHumidity(parsed.humidity());
            toSaveParsed.setTemperature(parsed.temperature());
            toSaveParsed.setTimestamp(localDateTime);

            weatherDataService.save(toSaveParsed);
        } catch (InterruptedException | FetchException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
