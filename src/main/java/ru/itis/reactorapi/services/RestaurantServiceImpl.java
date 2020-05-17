package ru.itis.reactorapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.reactorapi.clients.RestaurantClient;
import ru.itis.reactorapi.entries.RestaurantStatistic;
import ru.itis.reactorapi.parsers.JsonParser;
import ru.itis.reactorapi.repositories.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    private final List<RestaurantClient> clients;
    private final JsonParser parser;
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(JsonParser parser, List<RestaurantClient> clients, RestaurantRepository repository) {
        this.parser = parser;
        this.clients = clients;
        this.repository = repository;
    }

    @Override
    public Flux<RestaurantStatistic> getAll() {
        List<Flux<RestaurantStatistic>> fluxes = clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    @Override
    public void saveAll() {
        String path = "C:\\Users\\mecaz\\Desktop\\Repos\\JavaLabJuniorYear\\Reactor API\\src\\main\\resources\\yandexRestaurants.json";
        repository.saveAll(parser.parse(path));
    }

    private Flux<RestaurantStatistic> getAll(RestaurantClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
