package com.example.springwebflux;

import com.example.springwebflux.dto.BookDTO;
import com.example.springwebflux.model.Author;
import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.AuthorRepository;
import com.example.springwebflux.repository.BookRepository;
import com.example.springwebflux.service.BookService;
import com.example.springwebflux.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringWebfluxApplicationTests {

    @Mock // creates dependencies
    private BookRepository repo;

    @Mock // creates dependencies
    private AuthorRepository authorRepo;

    //@Autowired
    //private WebTestClient webTestClient;

    @InjectMocks // injects dependencies into test
    private BookServiceImpl service;

    @BeforeEach // prepares the environment
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBookDTO_Success() {
        Author author = new Author(1, "Rowling");
        BookDTO input = new BookDTO(null, "Test Book", author, "Fiction", LocalDate.now());
        Book saved = new Book(null, "Test Book", author.getId(), "Fiction", LocalDate.now());

        when(repo.save(any(Book.class))).thenReturn(Mono.just(saved)); // return the saved Book
        when(authorRepo.findById(author.getId())).thenReturn(Mono.just(author)); // return the author

        StepVerifier.create(service.createBook(input))
                .expectNextMatches(dto ->
                        dto.getTitle().equals("Test Book") &&
                                dto.getAuthor().getName().equals("Rowling") &&
                                dto.getGenre().equals("Fiction")
                )
                .verifyComplete();
    }

    /*
    @Test
    void createBook_ShouldReturnBookDTO() {
        Author author = new Author(1, "Rowling");
        BookDTO inputDTO = new BookDTO(null, "Test Book", author, "Fiction", LocalDate.now());
        BookDTO savedDTO = new BookDTO(1, "Test Book", author, "Fiction", LocalDate.now());

        // Mock the service behavior
        when(service.createBook(inputDTO)).thenReturn(Mono.just(savedDTO));

        webTestClient.post()
                .uri("/books")
                .bodyValue(inputDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDTO.class)
                .value(dto -> {
                    assertEquals("Test Book", dto.getTitle());
                    assertEquals("Rowling", dto.getAuthor().getName());
                });

        verify(service).createBook(inputDTO);
    }


     */
    /*
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



         */

}
