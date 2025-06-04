package ru.weather.aggregate.service;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ScheduledDataExtractor {
    private final List<DataExtractor> dataExtractors;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Scheduled(fixedRate = 20_000)
    public void extractData() {
        LocalDateTime now = LocalDateTime.now();
        // По - хорошему тут надо забрасывать сообщения в брокер с заданиями на запуск каждого дата экстрактора.
        // Для экономии времени использую экзекютор.
        if (!executorService.isShutdown()) {
            dataExtractors.forEach(dataExtractor -> {
                executorService.submit(() -> {
                    dataExtractor.extractData(now);
                });
            });
        }
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }
}
