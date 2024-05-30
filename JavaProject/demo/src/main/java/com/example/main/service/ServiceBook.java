package com.example.main.service;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;

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


//удаление всех книг
    public void deleteAllBooks();

//ищем по названию книги
    public List<Book> findByTitleOfBook(String titleOfBook);

//поиск по жанру
    public List<Book> findByGenre(Genre genre);

//поиск по статусу чтения
    public List<Book> findByReadingStatus(ReadingStatus readingStatus);

//поиск по оценке
    public List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);

//проверка существования книги по названию
    public boolean existsByTitleOfBookAndAuthors(String titleOfBook, Author author);

    boolean existById(int id);

}
