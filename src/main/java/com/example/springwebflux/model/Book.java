package com.example.springwebflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("book")
public class Book {
    @Id
    private Integer id; // Can be null before insert
    private String title;
    private Author author;
    private String genre;
    private LocalDate publishedDate;


    //
    public Book() {}

    public Book(String title) {
        this.title = title;
    }

}

