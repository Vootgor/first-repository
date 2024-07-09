package com.example.main.controller.only_for_authors;

import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления авторов */
@RestController
@RequestMapping("/library/authors/delete")
public class ControllerAuthorDeleted {

    @Autowired
    private ServiceAuthor serviceAuthor;

    /**
     * Метод для удаления автора из базы данных по id.
     * @param id число, которое является id автора в базе
     * @return возвращает String с информацией о том какой автор был удалён
     */
    @DeleteMapping("/author/{id}")
    public ResponseEntity<GeneralResponse<?>> deleteAuthor(@PathVariable int id) {
        Author author = serviceAuthor.getAuthor(id);
        if (author == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>("Данный автор отсутствует в базе"));
        }
        serviceAuthor.deleteAuthor(id);
        System.out.println("Удалили автора" + author);
        return ResponseEntity.status(204).body(new GeneralResponse<>("Был удален автор ", author));
    }



    /**
     * Метод удаляет всех авторов содержащихся в базе данных. Не будет доступен для пользователя.
     * @return возвращает String "Удалены все авторы"
     */
    @DeleteMapping("/allAuthors")
    public String deleteAllAuthors(){
        serviceAuthor.deletedAllAuthors();
        return "Удалены все авторы";
    }
}
