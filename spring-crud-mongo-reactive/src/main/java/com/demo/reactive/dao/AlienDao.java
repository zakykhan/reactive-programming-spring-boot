package com.demo.reactive.dao;

import com.demo.reactive.dto.AlienDto;
import com.demo.reactive.entity.Alien;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AlienDao extends ReactiveMongoRepository<Alien, String> {
    Flux<AlienDto> findByAgeBetween(Range<Integer> ageRange);
}
