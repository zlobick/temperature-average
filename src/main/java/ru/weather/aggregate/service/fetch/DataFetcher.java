package ru.weather.aggregate.service.fetch;

import ru.weather.aggregate.exceptions.FetchException;

import java.time.LocalDateTime;

public interface DataFetcher {
    String fetch(LocalDateTime dateTime) throws InterruptedException, FetchException;
}
