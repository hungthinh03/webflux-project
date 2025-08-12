package com.example.springwebflux.controller;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Controller

@RestController
@RequestMapping("/books") //Add "/books" automatically
public class Controller {
    @Autowired
    private BookService service;

    //Create
    @PostMapping
    public Mono<Book> createNewBook(@RequestBody Book book) { //(@RequestBody Book book)
        return service.createBook(book);
    }

    //Read
    @GetMapping("/all")
    public Flux<Book> showAllBooks() {
        return service.getAllBooks();
    }

    //
    @GetMapping("/{id}")
    public Mono<Book> showBookById(@PathVariable String id) {
        return service.getBookById(id);
    }

    //Update
    @PutMapping
    public Mono<Book> updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    //Delete
    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return service.deleteBookById(id);
    }


}

