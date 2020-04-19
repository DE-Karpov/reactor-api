package ru.itis.reactorapi.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.reactorapi.clients.JobClient;
import ru.itis.reactorapi.entries.JobStatistic;
import ru.itis.reactorapi.repositories.JobRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private List<JobClient> clients;

    @Autowired
    private JobRepository repository;


    @Override
    public Flux<JobStatistic> getAll() {
        List<Flux<JobStatistic>> fluxes = clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    @Override
    public void saveAll() {
        JSONParser parser = new JSONParser();
        List<JobStatistic> records = new ArrayList<>();
        Long id = 0L;
        try {
            JSONArray jobArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\mecaz\\Desktop\\Repos\\Reactor API\\src\\main\\resources\\usaGovJobs.json"));

            for (Object object : jobArray) {
                JSONObject jobRecord = (JSONObject) object;

                JobStatistic jobStatistic = JobStatistic.builder()
                        .id(id++)
                        .uuid((String) jobRecord.get("uuid"))
                        .title((String) jobRecord.get("title"))
                        .sourceFrom("Database")
                        .build();

                records.add(jobStatistic);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        repository.saveAll(records);
    }


    private Flux<JobStatistic> getAll(JobClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
