package com.example.springwebflux.service;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repo;

    @Override
    public Mono<Book> createBook(Book book) {
        return repo.save(book);
    }

    @Override
    public Flux<Book> getAllBooks() {
        return repo.findAll();
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return repo.findById(id);
    }
}
