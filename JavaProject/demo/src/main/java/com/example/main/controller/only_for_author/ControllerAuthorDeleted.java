package com.example.main.controller.only_for_author;

import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAuthorDeleted {

    @Autowired
    ServiceAuthor serviceAuthor;

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
