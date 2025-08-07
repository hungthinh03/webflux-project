package com.example.springwebflux.controller;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@org.springframework.stereotype.Controller

@RestController
@RequestMapping("/books") //Add "/books" automatically
public class Controller {
    @Autowired
    private BookService service;


    @GetMapping("/testPrint")
    public String testPrint() {
        return "Hello";
    }

    /*
    @PostMapping
    public Mono<Book> createBook(@RequestBody Book book) { //(@RequestBody Book book)
        return service.createBook(book);
    }
    */

    @GetMapping("/test")
    public Mono<Book> createTestBook() {
        Book book = new Book("2", "Kingdom Hearts");
        return service.createBook(book);
    }


    @GetMapping("/all")
    public Mono<List<Book>> showAllBooks() {
        return service.getAllBooks().collectList();
    }


}

