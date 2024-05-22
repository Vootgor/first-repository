package com.example.main.controller.only_for_books;

import com.example.main.entity.Book;
import com.example.main.entity.utilities.CheckingAddBook;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBooksSaveAndUpdate {

    @Autowired
    private ServiceBook serviceBook;

    @PostMapping("/library/books/add")
    public Book saveBook(@RequestBody Book book) {
        Book parseBook = CheckingAddBook.checkingTransmittedArgumentsForBook(
                book.getTitleOfBook()
                , book.getGenre()
                , book.getQuantityOfPage()
                , book.getReadingStatus()
                , book.getEvaluationOfBook()
                , book.getCommentOfBook()
                , book.getBookAddedDate()
                , book.getBookWasReadDate()
                , book.getAuthors()
                , book.getBookFiles()
        );

        serviceBook.saveOrUpdateBook(parseBook);
        return parseBook;
    }

    @PutMapping("/library/books/update")
    public Book updateBook(@RequestBody Book book) {
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

}
