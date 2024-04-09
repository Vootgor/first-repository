package com.example.main.entity;

import com.example.main.entity.abstractions.Literature;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "books")
public final class Book extends Literature {

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

}
