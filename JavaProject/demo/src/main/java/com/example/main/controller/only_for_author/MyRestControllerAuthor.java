package com.example.main.controller.only_for_author;

import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestControllerAuthor {

    @Autowired
    public ServiceAuthor serviceAuthor;


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

// сохранение автора
    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author) {
        List<Author> otherAuthors = serviceAuthor.findByAuthorFullName(author.getAuthorName()
                , author.getAuthorLastName(), author.getAuthorPatronymic());
        if (!otherAuthors.isEmpty()) {
            System.out.println("Данный автор уже существует");
            otherAuthors.forEach(System.out::println);
            return author;
        }
        serviceAuthor.saveOrUpdateAuthor(author);
        return author;
    }

    @PostMapping("/authors/saveAllAuthors")
    public String saveAllAuthors(@RequestBody List<Author> authors){
        serviceAuthor.saveAllAuthors(authors);
        return "Добавленно " + authors.size();
    }

// изменение автора
    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody Author author) {
        serviceAuthor.saveOrUpdateAuthor(author);
        return author;
    }

// удаление автора
    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable int id) {
        Author author = serviceAuthor.getAuthor(id);
        serviceAuthor.deleteAuthor(id);
        return "Was deleted author " + author + " with id " + id;
    }

    @DeleteMapping("/library/author/delete/allAuthors")
    public String deleteAllAuthors(){
        serviceAuthor.deletedAllAuthors();
        return "Удалены все авторы";
    }

}
