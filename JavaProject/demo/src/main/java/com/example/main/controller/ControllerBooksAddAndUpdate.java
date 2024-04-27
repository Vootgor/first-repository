package com.example.main.controller;

import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBooksAddAndUpdate {

    @Autowired
    private ServiceBook serviceBook;

    @PostMapping("/library/books/add")
    public Book saveBook(@RequestBody Book book){
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

    @PutMapping("/library/books/update")
    public Book updateBook(@RequestBody Book book){
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

}
