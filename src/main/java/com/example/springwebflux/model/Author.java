package com.example.springwebflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("author")
public class Author {
    @Id
    private Integer id; // Can be null before insert
    private String name;

    //
    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }
}