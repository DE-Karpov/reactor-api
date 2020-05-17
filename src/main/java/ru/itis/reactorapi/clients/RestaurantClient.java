package ru.itis.reactorapi.clients;

import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.RestaurantStatistic;

public interface RestaurantClient {
    Flux<RestaurantStatistic> getAll();
}
