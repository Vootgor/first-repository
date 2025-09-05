package com.bookstore.dao;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.enums.EvaluationOfBook;
import com.bookstore.entity.enums.Genre;
import com.bookstore.entity.enums.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBook extends JpaRepository<Book,Integer> {

    /**
     * Находит все книги с названием
     * @param titleOfBook название книги
     * @return возвращает список книг
     */
    List<Book> findByTitleOfBook(String titleOfBook);


    /**
     * Находит все книги с нужным жанром
     * @param genre искомый жанр
     * @return возвращает список книг
     */
    List<Book> findByGenre(Genre genre);


    /**
     * Находит все книги с нужным статусом чтения
     * @param readingStatus статус чтения
     * @return возвращает список книг
     */
    List<Book> findByReadingStatus(ReadingStatus readingStatus);


    /**
     * Находит все книги с нужно оценкой
     * @param evaluationOfBook оценка книги
     * @return возвращает список книг
     */
    List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);


    /**
     * проверить существование книги
     * @param titleOfBook название книги
     * @param author автор книги
     * @return возвращает true или false
     */
    boolean existsByTitleOfBookAndAuthors(String titleOfBook, Author author);

}
