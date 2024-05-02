package com.example.main.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public final class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "author_name")
    String authorName;

    @Column(name = "author_last_name")
    String authorLastName;

    @Column(name = "author_patronymic")
    String authorPatronymic;

    @JsonIgnoreProperties(value = "authors")
    @ManyToMany(cascade = {CascadeType.PERSIST
            ,CascadeType.MERGE, CascadeType.REFRESH , CascadeType.DETACH}
            , fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;//связь с табл books_authors

    public Author() {
    }
    @JsonCreator
    public Author(String authorName, String authorLastName, String authorPatronymic) {
        if (authorName == null){
            throw new IllegalArgumentException("Имя автора не может быть null");
        }
        if (authorLastName == null){
            throw new IllegalArgumentException("Фамилия автора не может быть null");
        }
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.authorPatronymic = authorPatronymic;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public void setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", AuthorName='" + authorName + '\'' +
                ", AuthorLastName='" + authorLastName + '\'' +
                ", AuthorPatronymic='" + authorPatronymic + '\'' +
                '}';
    }

}
