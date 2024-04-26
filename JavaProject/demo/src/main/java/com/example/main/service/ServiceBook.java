package com.example.main.service;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;

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

    public void deleteAllBooks();
//ищем по названию книги
    public List<Book> findByTitleOfBook(String titleOfBook);
//поиск по жанру
    public List<Book> findByGenre(Genre genre);
//поиск по статусу чтения
    public List<Book> findByReadingStatus(ReadingStatus readingStatus);
//поиск по оценке
    public List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);

}
