package ru.itis.reactorapi.services;

import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.JobStatistic;

public interface JobService {
    Flux<JobStatistic> getAll();

    void saveAll();
}