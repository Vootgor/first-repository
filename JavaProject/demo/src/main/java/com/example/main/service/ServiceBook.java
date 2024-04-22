package com.example.main.service;

import com.example.main.entity.Author;
import com.example.main.entity.Book;

import java.util.List;

public interface ServiceBook {

//---------------------------------------------------------
// Standard CRUD method
    public List<Book> getAllBooks();
    public Book getBook(int id);
    public void saveOrUpdateBook (Book book);
    public void deleteBook(int id);
//----------------------------------------------------------------------------
// Creating method

    //ищем по названию книги
    public List<Book> findByTitleOfBook(String titleOfBook);
}
