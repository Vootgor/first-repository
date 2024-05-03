package com.example.main.service;

import com.example.main.entity.Author;

import java.util.List;

public interface ServiceAuthor {


    //--------------------------------------------------------------------------
// Standard CRUD method

    public List<Author> getAllAuthors();
    public Author getAuthor(int id);
    public void saveOrUpdateAuthor (Author author);
    public void deleteAuthor(int id);


//----------------------------------------------------------------------------
// Creating method

    public void deletedAllAuthors();

    public List<Author> saveAllAuthors(List<Author> authors);

    public List<Author> findByAuthorLastName(String authorLastName);
    public List<Author> findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);
    public List<Author> findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(String authorName, String authorLastName, String authorPatronymic);

    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic);
    //проверка существования автора
    boolean existsByFIO(String authorName, String authorLastName, String authorPatronymic);
}
