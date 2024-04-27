package com.example.main.controller;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.service.ServiceAuthor;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;


    @PostMapping("/library/booksAndAuthors/add")
    public DtoAuthorsWithBooks saveBook(@RequestBody DtoAuthorsWithBooks dtoAuthorsWithBooks){

        Author author = dtoAuthorsWithBooks.getAuthor();
        Book book = dtoAuthorsWithBooks.getBook();

        serviceAuthor.saveOrUpdateAuthor(author);
        serviceBook.saveOrUpdateBook(book);
        return dtoAuthorsWithBooks;
    }


}
