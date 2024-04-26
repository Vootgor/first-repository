package com.example.main.dao;

import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.logging.Level;

public interface RepositoryBook extends JpaRepository<Book,Integer> {

    List<Book> findByTitleOfBook(String titleOfBook);

    List<Book> findByGenre(Genre genre);

    List<Book> findByReadingStatus(ReadingStatus readingStatus);

    List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook);
}
