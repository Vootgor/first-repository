package com.example.main.entity;

import com.example.main.entity.abstractions.Literature;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/** Entity class*/
@Entity
@Table(name = "books")
public final class Book extends Literature {

//    говорит о том что связь уже налажена классе Author поле books
    @JsonIgnoreProperties(value = "books")
    @ManyToMany(mappedBy = "books", cascade = {CascadeType.PERSIST
            ,CascadeType.MERGE, CascadeType.REFRESH , CascadeType.DETACH})
    private Set<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookFile> bookFiles; //связь с табл

    public Book() {
    }

    public Book(String titleOfBook, Genre genre, int quantityOfPage, ReadingStatus readingStatus,
                EvaluationOfBook evaluationOfBook, String commentOfBook, LocalDateTime bookAddedDate,
                LocalDateTime bookWasReadDate, Set<Author> authors, Set<BookFile> bookFiles) {
        super(titleOfBook, genre, quantityOfPage, readingStatus, evaluationOfBook, commentOfBook,
                bookAddedDate, bookWasReadDate);
        this.authors = authors;
        this.bookFiles = bookFiles;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BookFile> getBookFiles() {
        return bookFiles;
    }

    public void setBookFiles(Set<BookFile> bookFiles) {
        this.bookFiles = bookFiles;
    }

}
