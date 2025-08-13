package com.example.springwebflux.service;

import com.example.springwebflux.dto.BookDTO;
import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.AuthorRepository;
import com.example.springwebflux.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repo;
    @Autowired
    private AuthorRepository authorRepo;

    private Mono<BookDTO> toDTO(Book book) {
        return authorRepo.findById(book.getAuthorId())
                .map(author -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        author,        // full Author object
                        book.getGenre(),
                        book.getPublishedDate()
                ));
    }

    @Override
    public Mono<BookDTO> createBook(BookDTO dto) {
        return repo.save(new Book(null, dto.getTitle(), dto.getAuthor().getId(), dto.getGenre(), dto.getPublishedDate()))
                .flatMap(this::toDTO);
    }

    @Override
    public Flux<BookDTO> getAllBooks() {
        return repo.findAll()
                .flatMap(this::toDTO);
    }

    @Override
    public Mono<BookDTO> getBookById(Integer id) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found: " + id)))
                .flatMap(this::toDTO);
    }

    @Override
    public Mono<BookDTO> updateBook(BookDTO dto) {
        return Mono.justOrEmpty(dto.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Book id is required for update")))
                .flatMap(id -> repo.findById(id)
                        .switchIfEmpty(Mono.error(new RuntimeException("Book not found: " + id)))
                        .flatMap(existing -> {
                            existing.setTitle(dto.getTitle());
                            existing.setAuthorId(dto.getAuthor().getId());
                            existing.setGenre(dto.getGenre());
                            existing.setPublishedDate(dto.getPublishedDate());
                            return repo.save(existing);
                        })
                        .flatMap(this::toDTO) // Return DTO with full Author object
                );
    }

    @Override
    public Mono<Void> deleteBookById(Integer id) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found: " + id)))
                .flatMap(existing -> repo.deleteById(id)); // Delete if found
    }


}
