package com.example.springwebflux;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.BookRepository;
import com.example.springwebflux.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringWebfluxApplicationTests {

    @Mock // creates dummy dependencies
    private BookRepository repo;

    @InjectMocks // injects dummy dependencies into the class under test
    private BookServiceImpl service;

    private Book sampleBook;

    @BeforeEach // prepares the environment before each test case
    void setup() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book("Test Book");
    }

    @Test
    void createBook_Success() {
        Book book = sampleBook;
        when(repo.save(book)).thenReturn(Mono.just(book));

        Mono<Book> result = service.createBook(book);

        StepVerifier.create(result)
                .expectNextMatches(b -> b.getId().equals(1) && b.getTitle().equals("Test Book"))
                .verifyComplete();

        verify(repo).save(book); //
    }

    @Test
    void createBook_NullTitle_ShouldFail() {
        Book book = sampleBook;
        when(repo.save(book)).thenReturn(Mono.error(new RuntimeException("Title cannot be null")));

        Mono<Book> result = service.createBook(book);

        StepVerifier.create(result)
                .expectErrorMessage("Title cannot be null")
                .verify();

        verify(repo).save(book); //
    }

    @Test
    void createBook_DuplicateId_ShouldFail() {
        Book book = sampleBook;
        when(repo.save(book)).thenReturn(Mono.error(new RuntimeException("Duplicate key")));

        Mono<Book> result = service.createBook(book);

        StepVerifier.create(result)
                .expectErrorMessage("Duplicate key")
                .verify();

        verify(repo).save(book);
    }


    // READ: getBookById
    @Test
    void testGetBookById_Found() {
        when(repo.findById(1)).thenReturn(Mono.just(sampleBook));

        StepVerifier.create(service.getBookById(1))
                .expectNextMatches(book -> book.getId().equals(1) && book.getTitle().equals("Test Book"))
                .verifyComplete();
    }

    @Test
    void testGetBookById_NotFound() {
        when(repo.findById(2)).thenReturn(Mono.empty());

        StepVerifier.create(service.getBookById(2))
                .expectComplete()
                .verify();
    }

    // UPDATE: updateBook
    @Test
    void testUpdateBook_Success() {
        Book updated = new Book("Updated Title");
        when(repo.findById(1)).thenReturn(Mono.just(sampleBook));
        when(repo.save(any(Book.class))).thenReturn(Mono.just(updated));

        StepVerifier.create(service.updateBook(updated))
                .expectNextMatches(book -> book.getTitle().equals("Updated Title"))
                .verifyComplete();
    }

    @Test
    void testUpdateBook_NotFound() {
        Book updated = new Book("Updated Title");
        when(repo.findById(2)).thenReturn(Mono.empty());

        StepVerifier.create(service.updateBook(updated))
                .expectError(RuntimeException.class)
                .verify();
    }

    // DELETE: deleteBook
    @Test
    void testDeleteBook_Success() {
        when(repo.deleteById(1)).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteBookById(1))
                .verifyComplete();
    }

}
