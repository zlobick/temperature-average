package ru.weather.aggregate.service.fetch;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

@Component
public class DataFetcherThird extends AbstractDataFetcher {
    private final Random random = new Random();
    @Override
    public String doFetch(LocalDateTime dateTime) {
        return String.format(Locale.US, "{ \"weather\": { \"t\": %.2f, \"h\": %.2f } }", random.nextDouble(), random.nextDouble());
    }
}
