package com.example.springwebflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("book")
public class Book implements Persistable<String> {
    @Id
    private String id;
    private String title;

    @Transient  // Not stored in DB
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }



    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }
}

