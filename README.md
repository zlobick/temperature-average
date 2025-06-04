# Weather aggregator project
- *ru.weather.aggregate.service.fetch.DataFetcher* data fetchers fetches data from sources
- *ru.weather.aggregate.service.parse.DataParser* data parsers parses data respectively fetcher's format
- *ru.weather.aggregate.service.DataExtractor* data extractors combines fetchers and parsers and also stores data
- *ru.weather.aggregate.service.ScheduledDataExtractor* schedules data extraction
- aggregated data is exposed at **localhost:8080/weather/aggregate**
- Postgresql used as database. See docker compose file.
