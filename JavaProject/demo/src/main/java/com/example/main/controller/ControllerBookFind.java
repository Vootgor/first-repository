package com.example.main.controller;

import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerBookFind {

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



    @GetMapping("/books/findByTitleOfBook")
    public List<Book> showBooksByTitleOfBook(@RequestParam String titleOfBook){
        return serviceBook.findByTitleOfBook(titleOfBook);
    }

    @GetMapping("/books/genre/{genre}")
    public List<Book> showBooksByGenre(@PathVariable Genre genre){
        return serviceBook.findByGenre(genre);
    }

    @GetMapping("/books/readingStatus/{readingStatus}")
    public List<Book> showBooksByReadingStatus(@PathVariable ReadingStatus readingStatus){
        return serviceBook.findByReadingStatus(readingStatus);
    }

    @GetMapping("/books/evaluationOfBook/{evaluationOfBook}")
    public List<Book> showBooksByEvaluation(@PathVariable EvaluationOfBook evaluationOfBook){
        return serviceBook.findByEvaluationOfBook(evaluationOfBook);
    }



}
