package ru.itis.reactorapi.services;

import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.RestaurantStatistic;

public interface RestaurantService {

    Flux<RestaurantStatistic> getAll();

    void saveAll();
}