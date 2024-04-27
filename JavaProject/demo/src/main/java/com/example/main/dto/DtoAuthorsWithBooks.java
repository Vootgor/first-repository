package com.example.main.dto;

import com.example.main.entity.Author;
import com.example.main.entity.Book;

public class DtoAuthorsWithBooks {

    private Book book;
    private Author author;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
