package com.example.main.controller.only_for_author;

import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления авторов */
@RestController
public class ControllerAuthorDeleted {

    @Autowired
    private ServiceAuthor serviceAuthor;

    /**
     * Метод для удаления автора из базы данных по id.
     * @param id число которое является id автора в базе
     * @return возвращает String с информацией о том какой автор был удалён
     */
    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable int id) {
        Author author = serviceAuthor.getAuthor(id);
        serviceAuthor.deleteAuthor(id);
        return "Was deleted author " + author + " with id " + id;
    }

    /**
     * Метод удаляет всех авторов содержащихся в базе данных
     * @return возвращает String "Удалены все авторы"
     */
    @DeleteMapping("/library/author/delete/allAuthors")
    public String deleteAllAuthors(){
        serviceAuthor.deletedAllAuthors();
        return "Удалены все авторы";
    }
}
