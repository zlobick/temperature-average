package ru.weather.aggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AggregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregateApplication.class, args);
	}

}
