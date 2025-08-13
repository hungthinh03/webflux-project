package com.example.springwebflux.service;

import com.example.springwebflux.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<Book> createBook(Book book);

    //
    Flux<Book> getAllBooks();

    //
    Mono<Book> getBookById(Integer id);

    //
    Mono<Book> updateBook(Book book);

    //
    Mono<Void> deleteBookById(Integer id);

}