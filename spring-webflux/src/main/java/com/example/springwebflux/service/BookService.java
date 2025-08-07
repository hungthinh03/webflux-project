package com.example.springwebflux.service;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public Mono<Book> createBook(Book book) {
        return repo.save(book);
    }

    public Flux<Book> getAllBooks() {
        return repo.findAll();
    }

    /*
    public Mono<Book> getBookById(String id) {
        return repo.findById(id);
    }


     */
}