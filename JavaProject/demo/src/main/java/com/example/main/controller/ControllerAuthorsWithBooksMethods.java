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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;


    //todo добавить логику которая будет добавлять книгу к текущему автору если он уже есть
    //todo переписать извлечение автора из коллекции с помощью Stream
    @PostMapping("/library/booksAndAuthors/add")
    public String saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {

        Author parseAuthor = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(bookAuthorDTO.getAuthorName()
                , bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        System.out.println("Спарсили " + parseAuthor);

        System.out.println("Ищем " + parseAuthor.getAuthorName() + " "
                + parseAuthor.getAuthorLastName() + " " + parseAuthor.getAuthorPatronymic());

        List<Author> otherAuthors;
        Author otherAuthor;


        if (serviceAuthor.existsByFIO(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                , parseAuthor.getAuthorPatronymic())) {
            otherAuthors = serviceAuthor.findByAuthorFullName(parseAuthor.getAuthorName()
                    , parseAuthor.getAuthorLastName(), parseAuthor.getAuthorPatronymic());
            try {
                if (otherAuthors.size() > 1) {
                    throw new IllegalArgumentException("Существует более одно автора с данным ФИО");
                } else {
                    otherAuthor = otherAuthors.get(0);
                }
            } finally {
                System.out.println("Найдены авторы");
                otherAuthors.forEach(System.out::println);
            }
        } else {
            otherAuthor = new Author(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                    , parseAuthor.getAuthorPatronymic());
            serviceAuthor.saveOrUpdateAuthor(otherAuthor);
        }

        Book book = new Book(bookAuthorDTO.getTitleOfBook(), bookAuthorDTO.getGenre(), bookAuthorDTO.getQuantityOfPage(),
                bookAuthorDTO.getReadingStatus(), bookAuthorDTO.getEvaluationOfBook(), bookAuthorDTO.getCommentOfBook(),
                LocalDateTime.now(), null, Set.of(otherAuthor), null);

        serviceBook.saveOrUpdateBook(book);
        serviceAuthor.bindBookToAuthor(book.getId(), otherAuthor.getId());
        return "Добавлена книга " + book + "\nДобавлен автор " + otherAuthor;
    }


}
