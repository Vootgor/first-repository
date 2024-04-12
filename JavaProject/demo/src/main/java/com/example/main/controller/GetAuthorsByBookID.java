package com.example.main.controller;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.service.ServiceAuthor;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public class GetAuthorsByBookID {

    @Autowired
    private ServiceBook serviceBook;

    private ServiceAuthor serviceAuthor;

//    @GetMapping("/bookss/{id}")
//    public Book showAuthorsByBookId(@PathVariable int id){
//        List<Author> authors = serviceAuthor.getAuthorsByBookId(id);
//        System.out.println(authors);
//        return serviceBook.getBook(id);
//    }
}
