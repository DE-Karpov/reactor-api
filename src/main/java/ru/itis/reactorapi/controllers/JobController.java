package ru.itis.reactorapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.JobStatistic;
import ru.itis.reactorapi.services.JobService;

@RestController
@RequestMapping("/job-management")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping(value = "/records", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<JobStatistic> getAll() {
        return jobService.getAll();
    }

    @GetMapping(value = "/records/db")
    public void saveAll() {
        jobService.saveAll();
    }

}