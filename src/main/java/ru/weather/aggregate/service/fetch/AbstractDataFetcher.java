package ru.weather.aggregate.service.fetch;

import ru.weather.aggregate.exceptions.FetchException;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class AbstractDataFetcher implements DataFetcher {
    private final Random random = new Random();

    public abstract String doFetch(LocalDateTime dateTime);

    /**
     * В этом методе могла бы быть какая-нибудь общая лоогика, например обработка ошибок
     * или retry. Вместо этого мы добавим сюда случайные аварии.
     * @param dateTime время, для которого запрашиваются данные
     * @return строка, полученная от источника данных
     * @throws InterruptedException, FetchException
     */
    @Override
    public String fetch(LocalDateTime dateTime) throws InterruptedException, FetchException {
        int dice = random.nextInt(100);
        if (dice < 5) {
            Thread.sleep(3_000);
        } if (dice < 8) {
            return doFetch(dateTime).substring(4);
        } if (dice < 10) {
            throw new FetchException();
        }

        return doFetch(dateTime);
    }
}
