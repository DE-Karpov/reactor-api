package ru.itis.reactorapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.RestaurantStatistic;
import ru.itis.reactorapi.services.RestaurantService;

@RestController
@RequestMapping("/restaurant-management")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/records", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<RestaurantStatistic> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/records/db")
    public void saveAll() {
        restaurantService.saveAll();
    }

}