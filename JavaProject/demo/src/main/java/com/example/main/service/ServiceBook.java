package com.example.main.service;

import com.example.main.entity.Author;
import com.example.main.entity.Book;

import java.util.List;

public interface ServiceBook {

    public List<Book> getAllBooks();
    public Book getBook(int id);
    public void saveOrUpdateBook (Book book);
    public void deleteBook(int id);
}
