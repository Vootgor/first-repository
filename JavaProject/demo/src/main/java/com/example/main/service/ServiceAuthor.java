package com.example.main.service;

import com.example.main.entity.Author;

import java.util.List;

public interface ServiceAuthor {

    //todo модификаторы public не нужны в интерфейсах, потому что их методы всегда public
    //--------------------------------------------------------------------------
// Standard CRUD method

    public List<Author> getAllAuthors();
    public Author getAuthor(int id);
    public void saveOrUpdateAuthor (Author author);
    public void deleteAuthor(int id);


//----------------------------------------------------------------------------
// Creating method
    //todo опасный метод
    public void deletedAllAuthors();

    public void saveAllAuthors(List<Author> authors);

    public List<Author> findByAuthorLastName(String authorLastName);
    public List<Author> findByAuthorNameAndLastName(String authorName, String authorLastName);
    public List<Author> findByAuthorNameAndLastNameAndPatronymic(String authorName, String authorLastName, String authorPatronymic);

    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic);

    //проверка существования автора
    boolean existsByFIO(String authorName, String authorLastName, String authorPatronymic);

    //проверка существования id
    boolean existsById(int id);

    public void bindBookToAuthor(int bookId, int authorId);
}
