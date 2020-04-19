package ru.itis.reactorapi.clients;

import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.JobStatistic;

public interface JobClient {
    Flux<JobStatistic> getAll();
}
