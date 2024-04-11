package com.example.main.service;

import com.example.main.entity.Author;

import java.util.List;
import java.util.Set;

public interface ServiceAuthor {

    public List<Author> getAllAuthors();
    public Author getAuthor(int id);
    public void saveOrUpdateAuthor (Author author);

    public void deleteAuthor(int id);
}
