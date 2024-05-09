package com.example.main.controller;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.utilities.CheckingAddAuthor;
import com.example.main.service.ServiceAuthor;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;

    @PostMapping("/test")
    public String test(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {
        System.out.println(bookAuthorDTO);
        boolean b = serviceAuthor.existsByFIO(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        return String.valueOf(b);
    }


    //todo добавить логику которая будет добавлять книгу к текущему автору если он уже есть
    @PostMapping("/library/booksAndAuthors/add")
    public String saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {

        Author parseAuthor = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(bookAuthorDTO.getAuthorName()
                , bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        System.out.println("Спарсили " + parseAuthor);

        System.out.println("Ищем " + parseAuthor.getAuthorName()
                + parseAuthor.getAuthorLastName() + parseAuthor.getAuthorPatronymic() );
        List<Author> otherAuthors = serviceAuthor.findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(parseAuthor.getAuthorName()
                , parseAuthor.getAuthorLastName(), parseAuthor.getAuthorPatronymic());

        if (!otherAuthors.isEmpty()) {
            otherAuthors.forEach(System.out::println);
            return "Автор уже есть. Метод saveBookAndAuthor";
        }

        Author author = new Author(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                , parseAuthor.getAuthorPatronymic());
        serviceAuthor.saveOrUpdateAuthor(author);

        Book book = new Book(bookAuthorDTO.getTitleOfBook(), bookAuthorDTO.getGenre(), bookAuthorDTO.getQuantityOfPage(),
                bookAuthorDTO.getReadingStatus(), bookAuthorDTO.getEvaluationOfBook(), bookAuthorDTO.getCommentOfBook(),
                LocalDateTime.now(), null, Set.of(author), null);
        serviceBook.saveOrUpdateBook(book);
        serviceAuthor.insertBookAuthor(book.getId(), author.getId());
        return "Добавлена книга " + book + "\nДобавлен автор " + author;
    }


}
