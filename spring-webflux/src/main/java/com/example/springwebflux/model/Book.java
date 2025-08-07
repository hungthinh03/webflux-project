package com.example.springwebflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("book")
public class Book {
    @Id
    private String id;
    private String title;

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }
}