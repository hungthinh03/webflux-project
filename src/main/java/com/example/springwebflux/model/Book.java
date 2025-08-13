package com.example.springwebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("book")
public class Book {
    @Id
    private Integer id; // Can be null before insert
    private String title;
    private Integer authorId;  // foreign key
    private String genre;
    private LocalDate publishedDate;

    public Book(String title) {
        this.title = title;
    }

}

