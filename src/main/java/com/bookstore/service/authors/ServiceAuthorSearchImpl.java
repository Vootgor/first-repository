package com.bookstore.service.authors;

import com.bookstore.dao.RepositoryAuthor;
import com.bookstore.entity.Author;
import com.bookstore.service.ServiceAuthor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAuthorSearchImpl implements ServiceAuthor {

    @Autowired
    private RepositoryAuthor repositoryAuthor;



    @Override
    public List<Author> getAllAuthors() {
        return repositoryAuthor.findAll();
    }



    @Override
    public Author getAuthor(int id) {
        return repositoryAuthor.findById(id).orElse(null);
    }



    @Override
    @Transactional
    public void saveOrUpdateAuthor(Author author) {
        repositoryAuthor.save(author);
    }



    @Override
    @Transactional
    public void saveAllAuthors(List<Author> authors) {
        repositoryAuthor.saveAll(authors);
    }



    @Override
    @Transactional
    public void deleteAuthor(int id) {
        repositoryAuthor.deleteById(id);
    }



    @Override
    @Transactional
    public void deletedAllAuthors() {
        repositoryAuthor.deleteAll();
    }



    @Override
    public List<Author> findByAuthorLastName(String authorLastName) {
        return repositoryAuthor.findByAuthorLastName(authorLastName);
    }



    @Override
    public List<Author> findByAuthorNameAndLastName(String authorName, String authorLastName) {
        return repositoryAuthor.findByAuthorNameAndAuthorLastName(authorName, authorLastName);
    }




    @Override
    public List<Author> findByAuthorNameAndLastNameAndPatronymic(String authorName, String authorLastName, String authorPatronymic) {
        return repositoryAuthor.findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(authorName, authorLastName, authorPatronymic);
    }



    @Override
    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic) {
        return repositoryAuthor.findByAuthorFullName(authorName,authorLastName,authorPatronymic);
    }



    @Override
    public boolean existsByFIO(String authorName, String authorLastName, String authorPatronymic) {
        return repositoryAuthor.existsByAuthorNameAndAuthorLastNameAndAuthorPatronymic(authorName, authorLastName, authorPatronymic);
    }

    @Override
    public boolean existsById(int id) {
        return repositoryAuthor.existsById(id);
    }


    @Override
    @Transactional
    public void bindBookToAuthor(int bookId, int authorId) {
        repositoryAuthor.bindBookToAuthor(bookId,authorId);
    }


}
