package com.example.main.controller;

import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBookDeleted {

    @Autowired
    private ServiceBook serviceBook;

    @DeleteMapping("/library/books/delete/{id}")
    public String deleteBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        serviceBook.deleteBook(id);
        return "Deleted book " + book + " with id " + id;
    }

    @DeleteMapping("/library/books/delete/allBooks")
    public String deletedAllBooks(){
        serviceBook.deleteAllBooks();
        return "Deleted all books";
    }
}
