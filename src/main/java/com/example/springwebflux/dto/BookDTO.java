package com.example.springwebflux.dto;

import com.example.springwebflux.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private Author author;
    private String genre;
    private LocalDate publishedDate;


}
