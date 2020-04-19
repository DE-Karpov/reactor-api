package ru.itis.reactorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reactorapi.entries.JobStatistic;

@Repository
public interface JobRepository extends JpaRepository<JobStatistic, Long> {
}
