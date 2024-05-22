package com.example.main.controller.only_for_author;

import com.example.main.entity.Author;
import com.example.main.entity.utilities.CheckingAddAuthor;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerAuthorSaveOrUpdate {

    @Autowired
    public ServiceAuthor serviceAuthor;

// сохранение автора
//    @PostMapping("/authors")
//    public Author saveAuthor(@RequestBody Author author) {
//        List<Author> otherAuthors = serviceAuthor.findByAuthorFullName(author.getAuthorName()
//                , author.getAuthorLastName(), author.getAuthorPatronymic());
//        if (!otherAuthors.isEmpty()) {
//            System.out.println("Данный автор уже существует");
//            otherAuthors.forEach(System.out::println);
//            return author;
//        }
//        serviceAuthor.saveOrUpdateAuthor(author);
//        return author;
//    }

    @PostMapping("/add/authors")
    public Author saveAuthor(@RequestBody Author author) {
        Author otherAuthor = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(
                author.getAuthorName()
                , author.getAuthorLastName()
                , author.getAuthorPatronymic()
        );
        System.out.println("Спарсили " + otherAuthor);
        if (serviceAuthor.existsByFIO(otherAuthor.getAuthorName()
                ,otherAuthor.getAuthorLastName(),otherAuthor.getAuthorPatronymic())){
            throw new  IllegalArgumentException("Данный автор уже существует, метод saveAuthor");
        }else
            serviceAuthor.saveOrUpdateAuthor(otherAuthor);

        return otherAuthor;
    }

    //todo с помощью Stream прогнать лист входящих авторов через метод checkingTransmittedArgumentsForAuthor
    @PostMapping("/authors/saveAllAuthors")
    public String saveAllAuthors(@RequestBody List<Author> authors) {
        List<Author> otherAuthors;
        return "Добавленно " + authors.size();
    }

    // изменение автора
    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody Author author) {
        serviceAuthor.saveOrUpdateAuthor(author);
        return author;
    }


}
