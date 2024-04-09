package com.example.main.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "author_name")
    String AuthorName;

    @Column(name = "author_last_name")
    String AuthorLastName;

    @Column(name = "author_patronymic")
    String AuthorPatronymic;

    @ManyToMany(cascade = {CascadeType.PERSIST
            ,CascadeType.MERGE, CascadeType.REFRESH , CascadeType.DETACH}
            , fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    Set<Book> books;


    public Author() {
    }

    public Author(String authorName, String authorLastName, String authorPatronymic) {
        AuthorName = authorName;
        AuthorLastName = authorLastName;
        AuthorPatronymic = authorPatronymic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        AuthorLastName = authorLastName;
    }

    public String getAuthorPatronymic() {
        return AuthorPatronymic;
    }

    public void setAuthorPatronymic(String authorPatronymic) {
        AuthorPatronymic = authorPatronymic;
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
                ", AuthorName='" + AuthorName + '\'' +
                ", AuthorLastName='" + AuthorLastName + '\'' +
                ", AuthorPatronymic='" + AuthorPatronymic + '\'' +
                '}';
    }
}
