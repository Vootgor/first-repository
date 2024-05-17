package com.example.main.controller.only_for_books;

import com.example.main.dto.GeneralResponse;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GeneralResponse<Book>> showBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        if (book == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>("book is not found", null));
        }
        return ResponseEntity.ok(new GeneralResponse<>(null,book));
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
