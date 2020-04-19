package ru.itis.reactorapi.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.GithubJobsRecord;
import ru.itis.reactorapi.entries.JobStatistic;

import java.util.Arrays;

@Component
public class GithubJobApiClientWebClientImpl implements JobClient {

    private final WebClient client;

    public GithubJobApiClientWebClientImpl(@Value("${github.jobs.api.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<JobStatistic> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(GithubJobsRecord[].class))
                .flatMapIterable(Arrays::asList)
                .map(record ->
                        JobStatistic.builder()
                                .uuid(record.getId())
                                .title(record.getTitle())
                                .sourceFrom("GithubJobsAPI")
                                .build());
    }
}
