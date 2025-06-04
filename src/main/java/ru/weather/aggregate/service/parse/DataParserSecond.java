package ru.weather.aggregate.service.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataParserSecond extends AbstractJsonDataParser<DataParserSecond.Dto> {
    @Getter(AccessLevel.PROTECTED)
    private final ObjectMapper objectMapper;

    @Override
    protected Class<Dto> getDtoClass() {
        return Dto.class;
    }

    @Override
    protected TemperatureData map(Dto dto) {
        return new TemperatureData(dto.temperature(), dto.humidity());
    }

    // Обычно некорректно хранить ДТО в сервис, но т.к. данное ДТО не является частью API,
    // данное отступление допустимо
    record Dto (Double temperature, double humidity) {}
}
