package com.example.springwebflux.repository;

import com.example.springwebflux.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, String> {
}

