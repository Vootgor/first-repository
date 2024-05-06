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


    @PostMapping("/library/booksAndAuthors/add")
    public String saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {
        // это проверка на существование автора в списке, не хватает метода который
        // будет вместо добавления нового автора, добавлять книгу к текущему
        List<Author> otherAuthors = serviceAuthor.findByAuthorFullName(bookAuthorDTO.getAuthorName()
        ,bookAuthorDTO.getAuthorLastName(),bookAuthorDTO.getAuthorPatronymic());

        if (!otherAuthors.isEmpty()){
            otherAuthors.forEach(System.out::println);
            return "Автор уже есть. Метод saveBookAndAuthor";
        }

                Author author = new Author(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        serviceAuthor.saveOrUpdateAuthor(author);

        Book book = new Book(bookAuthorDTO.getTitleOfBook(), bookAuthorDTO.getGenre(), bookAuthorDTO.getQuantityOfPage(),
                bookAuthorDTO.getReadingStatus(), bookAuthorDTO.getEvaluationOfBook(), bookAuthorDTO.getCommentOfBook(),
                LocalDateTime.now(), null, Set.of(author), null);
        serviceBook.saveOrUpdateBook(book);
        serviceAuthor.insertBookAuthor(book.getId(),author.getId());
        return "Добавлена книга " + book + "\nДобавлен автор " + author;
    }


}
