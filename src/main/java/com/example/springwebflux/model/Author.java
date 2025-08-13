package com.example.springwebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("author")
public class Author {
    @Id
    private Integer id; // Can be null before insert
    private String name;

    public Author(String name) {
        this.name = name;
    }
}