package com.example.main.dao;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBook extends JpaRepository<Book,Integer> {

    //найти по названию книги
    List<Book> findByTitleOfBook(String titleOfBook);

    // найти по жанру
    List<Book> findByGenre(Genre genre);

    //найти по статусу чтения
    List<Book> findByReadingStatus(ReadingStatus readingStatus);

    //найти по оценке книги
    List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);

    //проверить существование книги
    boolean existsByTitleOfBookAndAuthors(String titleOfBook, Author author);

}
