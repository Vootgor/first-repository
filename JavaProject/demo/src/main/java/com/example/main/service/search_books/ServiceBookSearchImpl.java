package com.example.main.service.search_books;

import com.example.main.dao.RepositoryBook;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import com.example.main.service.ServiceBook;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBookSearchImpl implements ServiceBook {

    @Autowired
    private RepositoryBook repositoryBook;

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return repositoryBook.findAll();
    }

    @Override
    @Transactional
    public Book getBook(int id) {
        Book book = null;
        Optional<Book> optional = repositoryBook.findById(id);
        if (optional.isPresent()){
            book = optional.get();
        }
        return book;
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
    @Transactional
    public List<Book> findByTitleOfBook(String titleOfBook) {
        return repositoryBook.findByTitleOfBook(titleOfBook);
    }

    @Override
    @Transactional
    public List<Book> findByGenre(Genre genre) {
        return repositoryBook.findByGenre(genre);
    }

    @Override
    @Transactional
    public List<Book> findByReadingStatus(ReadingStatus readingStatus) {
        return repositoryBook.findByReadingStatus(readingStatus);
    }

    @Override
    @Transactional
    public List<Book> findByEvaluationOfBook(EvaluationOfBook evaluationOfBook) {
        return repositoryBook.findByEvaluationOfBook(evaluationOfBook);
    }

}
