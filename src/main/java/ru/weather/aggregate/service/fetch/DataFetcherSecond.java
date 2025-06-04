package ru.weather.aggregate.service.fetch;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

@Component
public class DataFetcherSecond extends AbstractDataFetcher {
    private final Random random = new Random();
    @Override
    public String doFetch(LocalDateTime dateTime) {
        return String.format(Locale.US, "{ \"temperature\": %.2f, \"humidity\": %.2f }", random.nextDouble(), random.nextDouble());
    }
}
