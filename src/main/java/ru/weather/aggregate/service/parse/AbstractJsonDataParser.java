package ru.weather.aggregate.service.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.weather.aggregate.exceptions.ParseException;

@Slf4j
public abstract class AbstractJsonDataParser<DTO> implements DataParser {
    protected abstract ObjectMapper getObjectMapper();
    protected abstract Class<DTO> getDtoClass();
    protected abstract DataParser.TemperatureData map(DTO dto);

    @Override
    public DataParser.TemperatureData parseTemperature(String fetched) throws ParseException {
        try {
            log.info("Parsing temperature data from fetched: {}", fetched);
            DTO parsed = getObjectMapper().readValue(fetched, getDtoClass());
            log.info("Successfully parsed: {}", fetched);
            return map(parsed);
        } catch (JsonProcessingException e) {
            log.warn("unable to parse temperature data from fetched: " + fetched, e);
            throw new ParseException();
        }
    }
}
