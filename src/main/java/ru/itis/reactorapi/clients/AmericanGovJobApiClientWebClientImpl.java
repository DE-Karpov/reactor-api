package ru.itis.reactorapi.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.itis.reactorapi.entries.JobStatistic;
import ru.itis.reactorapi.repositories.JobRepository;

import java.util.List;

@Component
public class AmericanGovJobApiClientWebClientImpl implements JobClient {

    @Autowired
    private JobRepository repository;

    @Override
    public Flux<JobStatistic> getAll() {
        List<JobStatistic> records = repository.findAll();
        return Flux.fromIterable(records);
//                client.get()
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .flatMap(clientResponse -> clientResponse.bodyToMono(AmericanGovernmentJobsRecord.class)) // преобразуем данные со стороннего сервреа в Publisher
//                .flatMapIterable(Arrays::asList) // выполняем конвертацию данных с другого сервера в наши, возвращаем мы набор Publisher-ов(?) каждый из которых возвращает объект CovidStatistic
//                .map(record ->
//                        JobStatistic.builder()
//                                .uuid(record.getUuid())
//                                .title(record.getTitle())
//                                .sourceFrom("AmericanGovJobAPI")
//                                .build());
    }
}
