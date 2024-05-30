package com.example.main.controller.only_for_authors;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.entity.Author;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Класс является контроллером содержащим методы для поиска авторов */
@RestController
@RequestMapping("/library/authors/find")
public class ControllerAuthorFind {

    @Autowired
    private ServiceAuthor serviceAuthor;

    /**
     * Метод выводит всех авторов из базы данных
     * @return возвращает List<Author>
     */
    @GetMapping("/showAllAuthors")
    public ResponseEntity<GeneralResponse<List<Author>>> showAllAuthors() {
        return ResponseEntity.ok().body(new  GeneralResponse<>(serviceAuthor.getAllAuthors()));
    }



    /**
     * Метод находит автора по id и возвращает его
     * @param id число являющееся id автора
     * @return возвращает автора
     */
    @GetMapping("/showAuthor/{id}")
    public ResponseEntity<GeneralResponse<Author>> showAuthor(@PathVariable int id) {
        Author author = serviceAuthor.getAuthor(id);
        if (author == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>("Данный автор не найден в базе"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(author));
    }



    /**
     * Метод ищет автора в базе данных в зависимости от аргументов переданных в параметры метода.
     * Проверяет если имя и отчество null то ищет только по фамилии используя метод findByAuthorLastName,
     * если отчество null ищет по имени и фамилии с помощью метода findByAuthorNameAndAuthorLastName,
     * иначе ищет по-полному ФИО. Возвращает List найденных авторов.
     * @param authorName Строка являющаяся именем автора. Не обязательный аргумент, может не передаваться.
     * @param authorLastName строка являющаяся фамилией автора.
     * @param authorPatronymic Строка являющаяся отчеством автора. Не обязательный аргумент, может не передаваться.
     * @return возвращает List<Author> которые были найдены
     */
    @GetMapping("/showAuthorByFullName")
    public ResponseEntity<GeneralResponse<List<Author>>> showAuthorByFullName(
            @RequestParam(required = false) String authorName,
            @RequestParam() String authorLastName,
            @RequestParam(required = false) String authorPatronymic) {
        if (authorName == null && authorPatronymic == null) {
            return ResponseEntity.ok().body(new GeneralResponse<>(
                    serviceAuthor.findByAuthorLastName(authorLastName)));
        } else if (authorPatronymic == null) {
            return ResponseEntity.ok().body(new GeneralResponse<>(
                    serviceAuthor.findByAuthorNameAndLastName(authorName, authorLastName)));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(
                serviceAuthor.findByAuthorNameAndLastNameAndPatronymic(authorName, authorLastName, authorPatronymic)));
    }



    /** Метод принимает в параметр экземпляр класса DtoAuthorsWithBooks вытаскивает из него ФИО автора,
     * и проверяет в базе данных существование данного автора.
     * @param bookAuthorDTO экземпляр класса DtoAuthorsWithBooks
     * @return возвращает true если автор найден и false если автора нет в базе данных
     */
    @PostMapping("/checkingExistAuthor")
    public ResponseEntity<GeneralResponse<?>> checkingExistAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorDTO) {
        boolean b = serviceAuthor.existsByFIO(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorLastName(), bookAuthorDTO.getAuthorPatronymic());
        if (!b){
            ResponseEntity.status(404).body(new GeneralResponse<>("Данный автор не существует в базе"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(String.valueOf(b)));
    }

}
