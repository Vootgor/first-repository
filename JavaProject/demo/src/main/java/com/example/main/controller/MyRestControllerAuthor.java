package com.example.main.controller;

import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestControllerAuthor {

    @Autowired
    public ServiceAuthor serviceAuthor;

    @GetMapping("/authors")
    public List<Author> showAllAuthors(){
        return serviceAuthor.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author showAuthor(@PathVariable int id){
        return serviceAuthor.getAuthor(id);
    }

    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author){
        serviceAuthor.saveOrUpdateAuthor(author);
        return author;
    }

    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody Author author){
         serviceAuthor.saveOrUpdateAuthor(author);
         return author;
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable int id){
        Author author = serviceAuthor.getAuthor(id);
        serviceAuthor.deleteAuthor(id);
        return "Was deleted author "+ author +" with id " + id;
    }

}
