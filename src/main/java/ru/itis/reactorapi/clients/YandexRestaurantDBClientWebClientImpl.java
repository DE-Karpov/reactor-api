package ru.itis.reactorapi.clients;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.RestaurantStatistic;
import ru.itis.reactorapi.repositories.RestaurantRepository;

import java.time.Duration;

@Component
public class YandexRestaurantDBClientWebClientImpl implements RestaurantClient {

    private final RestaurantRepository repository;

    @Autowired
    public YandexRestaurantDBClientWebClientImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<RestaurantStatistic> getAll() {
        val records = repository.findAll();
        return Flux.fromIterable(records)
                .map(record -> RestaurantStatistic.builder()
                                .name(record.getName())
                                .address(record.getDescription())
                                .build())
                .delaySequence(Duration.ofMillis(100));
    }
}
