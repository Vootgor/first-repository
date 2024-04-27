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



    @GetMapping("/authors/findByAuthor")
    public List<Author> showAuthorByFullName(@RequestParam (required = false) String authorName, @RequestParam() String authorLastName
    , @RequestParam(required = false) String authorPatronymic){
        if (authorName == null && authorPatronymic == null){
            return serviceAuthor.findByAuthorLastName(authorLastName);
        }else if (authorPatronymic == null){
            return serviceAuthor.findByAuthorNameAndAuthorLastName(authorName,authorLastName);
        }return serviceAuthor.findByAuthorFullName(authorName,authorLastName,authorPatronymic);
    }


    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author){
        List <Author> otherAuthors = showAuthorByFullName(author.getAuthorName()
                ,author.getAuthorLastName(),author.getAuthorPatronymic());
        if (!otherAuthors.isEmpty()){
            System.out.println("Данный автор уже существует");
            for (Author author1 : otherAuthors){
                System.out.println(author1);
            }
            return author;
        }
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
