package com.example.main.service.search_books;

import com.example.main.dao.RepositoryBook;
import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Book book = null;
        Optional<Book> optional = repositoryBook.findById(id);
        if (optional.isPresent()){
            book = optional.get();
        }
        return book;
    }

    @Override
    public void saveOrUpdateBook(Book book) {
        repositoryBook.save(book);
    }

    @Override
    public void deleteBook(int id) {
        repositoryBook.deleteById(id);
    }

    @Override
    public List<Book> findByTitleOfBook(String titleOfBook) {
        return repositoryBook.findByTitleOfBook(titleOfBook);
    }


}
