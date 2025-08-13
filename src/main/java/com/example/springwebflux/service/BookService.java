package com.example.springwebflux.service;

import com.example.springwebflux.dto.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookDTO> createBook(BookDTO bookDTO);

    //
    Flux<BookDTO> getAllBooks();

    //
    Mono<BookDTO> getBookById(Integer id);

    //
    Mono<BookDTO> updateBook(BookDTO bookDTO);

    //
    Mono<Void> deleteBookById(Integer id);

}