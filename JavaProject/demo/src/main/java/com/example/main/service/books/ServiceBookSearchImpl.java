package com.example.main.service.books;

import com.example.main.dao.RepositoryBook;
import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;
import com.example.main.service.ServiceBook;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceBookSearchImpl implements ServiceBook {

    @Autowired
    private RepositoryBook repositoryBook;

    @Override
    public List<Book> getAllBooks() {
        return repositoryBook.findAll();
    }


    @Override
    public Book getBook(int id) {
        return repositoryBook.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public void saveOrUpdateBook(Book book) {
        repositoryBook.save(book);
    }


    @Override
    @Transactional
    public void deleteBook(int id) {
        repositoryBook.deleteById(id);
    }


    @Override
    @Transactional
    public void deleteAllBooks() {
        repositoryBook.deleteAll();
    }

    @Override
    public List<Book> findByTitleOfBook(String titleOfBook) {
        return repositoryBook.findByTitleOfBook(titleOfBook);
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return repositoryBook.findByGenre(genre);
    }

    @Override
    public List<Book> findByReadingStatus(ReadingStatus readingStatus) {
        return repositoryBook.findByReadingStatus(readingStatus);
    }

    @Override
    public List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook) {
        return repositoryBook.findByEvaluationOfBook(evaluationOfBook);
    }

    @Override
    public boolean existsByTitleOfBookAndAuthors(String titleOfBook, Author author) {
        return repositoryBook.existsByTitleOfBookAndAuthors(titleOfBook, author);
    }

    @Override
    public boolean existById(int id) {
        return repositoryBook.existsById(id);
    }


}
