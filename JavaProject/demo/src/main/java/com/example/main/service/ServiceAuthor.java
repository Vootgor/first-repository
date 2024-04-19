package com.example.main.service;

import com.example.main.entity.Author;

import java.util.List;
import java.util.Set;

public interface ServiceAuthor {


//--------------------------------------------------------------------------
// Standard CRUD method
    public List<Author> getAllAuthors();
    public Author getAuthor(int id);
    public void saveOrUpdateAuthor (Author author);
    public void deleteAuthor(int id);



//----------------------------------------------------------------------------
// Creating method
    public List<Author> findByAuthorLastName(String authorLastName);
    public List<Author> findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);
    public List<Author> findByAuthorFullName(String authorName, String authorLastName,String authorPatronymic);

}
