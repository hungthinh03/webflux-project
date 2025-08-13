package com.example.springwebflux.controller;

import com.example.springwebflux.dto.BookDTO;
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

    // Create
    @PostMapping
    public Mono<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return service.createBook(bookDTO);
    }

    //Read
    @GetMapping("/all")
    public Flux<BookDTO> showAllBooks() {
        return service.getAllBooks();
    }

    //
    @GetMapping("/{id}")
    public Mono<BookDTO> showBookById(@PathVariable Integer id) {
        return service.getBookById(id);
    }

    //Update
    @PutMapping
    public Mono<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return service.updateBook(bookDTO);
    }

    //Delete
    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable Integer id) {
        return service.deleteBookById(id);
    }


}

