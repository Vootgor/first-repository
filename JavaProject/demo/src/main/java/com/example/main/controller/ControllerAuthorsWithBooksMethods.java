package com.example.main.controller;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.service.ServiceAuthor;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;



    @PostMapping("/library/booksAndAuthors/add")
    public DtoAuthorsWithBooks saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {

        Author author = new Author(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        serviceAuthor.saveOrUpdateAuthor(author);

        Book book = new Book(bookAuthorDTO.getTitleOfBook(), bookAuthorDTO.getGenre(), bookAuthorDTO.getQuantityOfPage(),
                bookAuthorDTO.getReadingStatus(), bookAuthorDTO.getEvaluationOfBook(), bookAuthorDTO.getCommentOfBook(),
                LocalDateTime.now(), null, Set.of(author), null);
        serviceBook.saveOrUpdateBook(book);
        return bookAuthorDTO;
    }


}
