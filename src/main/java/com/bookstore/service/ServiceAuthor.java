package com.bookstore.service;

import com.bookstore.entity.Author;

import java.util.List;

public interface ServiceAuthor {

    //--------------------------------------------------------------------------
// Standard CRUD method

     List<Author> getAllAuthors();
     Author getAuthor(int id);
     void saveOrUpdateAuthor (Author author);
     void deleteAuthor(int id);


//----------------------------------------------------------------------------
// Creating method
     void deletedAllAuthors();

     void saveAllAuthors(List<Author> authors);

     List<Author> findByAuthorLastName(String authorLastName);
     List<Author> findByAuthorNameAndLastName(String authorName, String authorLastName);
     List<Author> findByAuthorNameAndLastNameAndPatronymic(String authorName, String authorLastName, String authorPatronymic);

     List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic);

    //проверка существования автора
    boolean existsByFIO(String authorName, String authorLastName, String authorPatronymic);

    //проверка существования id
    boolean existsById(int id);

     void bindBookToAuthor(int bookId, int authorId);
}
