package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.enums.EvaluationOfBook;
import com.bookstore.entity.enums.Genre;
import com.bookstore.entity.enums.ReadingStatus;

import java.util.List;

public interface ServiceBook {

//---------------------------------------------------------
// Standard CRUD method
     List<Book> getAllBooks();
     Book getBook(int id);
     void saveOrUpdateBook (Book book);
     void deleteBook(int id);


//----------------------------------------------------------------------------
// Creating method


//удаление всех книг
     void deleteAllBooks();

//ищем по названию книги
     List<Book> findByTitleOfBook(String titleOfBook);

//поиск по жанру
     List<Book> findByGenre(Genre genre);

//поиск по статусу чтения
     List<Book> findByReadingStatus(ReadingStatus readingStatus);

//поиск по оценке
     List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);

//проверка существования книги по названию
     boolean existsByTitleOfBookAndAuthors(String titleOfBook, Author author);

    boolean existById(int id);

}
