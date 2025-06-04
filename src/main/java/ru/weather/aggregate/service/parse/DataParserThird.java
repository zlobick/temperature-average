package ru.weather.aggregate.service.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataParserThird extends AbstractJsonDataParser<DataParserThird.Dto> {
    @Getter(AccessLevel.PROTECTED)
    private final ObjectMapper objectMapper;

    @Override
    protected Class<Dto> getDtoClass() {
        return Dto.class;
    }

    @Override
    protected TemperatureData map(Dto dto) {
        return new TemperatureData(
                Optional.of(dto).map(Dto::weather).map(InnerWeather::t).orElse(null),
                Optional.of(dto).map(Dto::weather).map(InnerWeather::h).orElse(null)
        );
    }

    // Обычно некорректно хранить ДТО в сервис, но т.к. данное ДТО не является частью API,
    // данное отступление допустимо
    record Dto (InnerWeather weather) {}
    record InnerWeather (Double t, double h) {}
}
