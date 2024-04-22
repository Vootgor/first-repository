package com.example.main.controller;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestControllerBook {

    @Autowired
    private ServiceBook serviceBook;

    @GetMapping("/books")
    public List<Book> showAllBooks(){
        return serviceBook.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book showBook(@PathVariable int id){
        return serviceBook.getBook(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book){
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book){
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        serviceBook.deleteBook(id);
        return "Deleted book " + book + " with id " + id;
    }

    @GetMapping("/books/findByTitleOfBook")
    public List<Book> showBooksByTitleOfBook(@RequestParam String titleOfBook){
        return serviceBook.findByTitleOfBook(titleOfBook);
    }

}
