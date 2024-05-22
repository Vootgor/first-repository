package com.example.main.controller.only_for_author;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerAuthorFind {

    @Autowired
    ServiceAuthor serviceAuthor;

    //вывод всех авторов
    @GetMapping("/authors")
    public List<Author> showAllAuthors() {
        return serviceAuthor.getAllAuthors();
    }

    // вывод автора по id
    @GetMapping("/authors/{id}")
    public Author showAuthor(@PathVariable int id) {
        return serviceAuthor.getAuthor(id);
    }

    // поиск автора по ФИО
    @GetMapping("/authors/findByAuthor")
    public List<Author> showAuthorByFullName(@RequestParam(required = false) String authorName, @RequestParam() String authorLastName
            , @RequestParam(required = false) String authorPatronymic) {
        if (authorName == null && authorPatronymic == null) {
            return serviceAuthor.findByAuthorLastName(authorLastName);
        } else if (authorPatronymic == null) {
            return serviceAuthor.findByAuthorNameAndAuthorLastName(authorName, authorLastName);
        }
        return serviceAuthor.findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(authorName, authorLastName, authorPatronymic);
    }

    /** Метод принимает в парамерт экземпляр класса DtoAuthorsWithBooks вытаскивает из него ФИО автора,
     * и проверяет в базе данных существование данного автора.
     *
     * @param bookAuthorDTO экзепляр класса DtoAuthorsWithBooks
     * @return возвращает true если автор найден и false если автора нет в базе данных
     */
    @PostMapping("/test")
    public String checkingExistAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {
        System.out.println(bookAuthorDTO);
        boolean b = serviceAuthor.existsByFIO(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        System.out.println("Метод test вернул " + b);
        return String.valueOf(b);
    }
}
