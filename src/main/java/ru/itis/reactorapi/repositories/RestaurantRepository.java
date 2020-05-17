package ru.itis.reactorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reactorapi.entries.YandexRestaurantRecord;

@Repository
public interface RestaurantRepository extends JpaRepository<YandexRestaurantRecord, Long> {

}
