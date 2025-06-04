CREATE TABLE raw_weather_data (
    id UUID primary key,
    source_id bigint not null,
    payload varchar(256),
    created_at timestamp not null
);
CREATE INDEX ON raw_weather_data (created_at);
CREATE TABLE normalized_weather_data (
    id UUID primary key,
    source_id bigint not null,
    temperature double precision, -- излишняя точность...
    humidity double precision, -- излишняя точность...
    created_at timestamp not null
);
CREATE INDEX ON normalized_weather_data (created_at);