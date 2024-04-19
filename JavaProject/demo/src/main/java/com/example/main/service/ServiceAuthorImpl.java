package com.example.main.service;

import com.example.main.dao.RepositoryAuthor;
import com.example.main.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceAuthorImpl implements ServiceAuthor{

    @Autowired
    private RepositoryAuthor repositoryAuthor;

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return repositoryAuthor.findAll();
    }

    @Override
    @Transactional
    public Author getAuthor(int id) {
        Author author = null;
        Optional<Author> optional = repositoryAuthor.findById(id);
        if (optional.isPresent()){
            author = optional.get();
        }
        return author;
    }

    @Override
    @Transactional
    public void saveOrUpdateAuthor(Author author) {
        repositoryAuthor.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        repositoryAuthor.deleteById(id);
    }

    @Override
    public List<Author> findByAuthorLastName(String authorLastName) {
        return repositoryAuthor.findByAuthorLastName(authorLastName);
    }

    @Override
    public List<Author> findByAuthorNameAndAuthorLastName(String authorName, String authorLastName) {
        return repositoryAuthor.findByAuthorNameAndAuthorLastName(authorName, authorLastName);
    }

    @Override
    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic) {
        return repositoryAuthor.findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(authorName, authorLastName,authorPatronymic);
    }

}
