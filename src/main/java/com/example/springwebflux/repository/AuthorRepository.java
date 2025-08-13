package com.example.springwebflux.repository;

import com.example.springwebflux.model.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Integer> {
    Flux<Author> findByName(String name);
}