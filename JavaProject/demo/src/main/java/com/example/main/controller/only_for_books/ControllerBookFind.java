package com.example.main.controller.only_for_books;

import com.example.main.entity.Author;
import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.entity.Book;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/** Класс является контроллером содержащим методы для поиска книг */
@RestController
@RequestMapping("/library/books/find")
public class ControllerBookFind {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод возвращает список всех книг в базе. Не будет доступен для пользователя.
     * @return возвращает список всех книг в базе.
     */
    @GetMapping("/showAllBooks")
    public ResponseEntity<GeneralResponse<List<Book>>> showAllBooks(){
        return ResponseEntity.ok().body(new GeneralResponse<>(serviceBook.getAllBooks()));
    }

    /**
     * Метод возвращает книгу по id или статус 404 если данной книги нет
     * @param id число являющееся id книги в базе
     * @return возвращает книгу по введённому id или статус 404 если данной книги нет
     */
    @GetMapping("/showBook/{id}")
    public ResponseEntity<GeneralResponse<Book>> showBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        if (book == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>("book is not found", null));
        }
        return ResponseEntity.ok(new GeneralResponse<>(null,book));
    }


    /**
     * Метод служит для поиска книг по названию
     * @param titleOfBook строка с названием книги
     * @return возвращает список книг с этим названием
     */
    @GetMapping("/showBooksByTitleOfBook")
    public ResponseEntity<GeneralResponse<List<Book>>> showBooksByTitleOfBook(@RequestParam String titleOfBook){
        List<Book> books = serviceBook.findByTitleOfBook(titleOfBook);
        if (books.isEmpty()){
            return ResponseEntity.status(404).body(new GeneralResponse<>(
                    "С данным названием не найдено ни одной книги"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(serviceBook.findByTitleOfBook(titleOfBook)));
    }

    /**
     * Метод служит для поиска книги по жанру
     * @param genre жанр книги
     * @return возвращает список книг с данным жанром
     */
    @GetMapping("/showBooksByGenre/{genre}")
    public ResponseEntity<GeneralResponse<List<Book>>> showBooksByGenre(@PathVariable Genre genre){
        List<Book> books = serviceBook.findByGenre(genre);
        if (books.isEmpty()){
            return ResponseEntity.status(404).body(new GeneralResponse<>("Книг с данным жанром не найдено"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(serviceBook.findByGenre(genre))) ;
    }

    /**
     * Метод служит для поиска книги по статусу чтения
     * @param readingStatus статус чтения
     * @return возвращает список книг с данным статусом
     */
    @GetMapping("/showBooksByReadingStatus/{readingStatus}")
    public ResponseEntity<GeneralResponse<List<Book>>> showBooksByReadingStatus(@PathVariable ReadingStatus readingStatus){
        List<Book> books = serviceBook.findByReadingStatus(readingStatus);
        if (books.isEmpty()){
            return ResponseEntity.status(404).body(new GeneralResponse<>(
                    "Книг с данным статусом чтения не найдено"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(serviceBook.findByReadingStatus(readingStatus)));
    }

    /**
     * Метод служит для поиска книги по её оценке
     * @param evaluationOfBook число являющееся оценкой книги
     * @return возвращает список книги с данной оценкой
     */
    //todo лучше сделать проверку через exist вместо сохранения в лист. А метод findByEvaluationOfBook использовать
    // только после этой проверки.
    @GetMapping("/showBooksByEvaluation/{evaluationOfBook}")
    public ResponseEntity<GeneralResponse<List<Book>>> showBooksByEvaluation(@PathVariable EvaluationOfBook evaluationOfBook){
        List<Book> books = serviceBook.findByEvaluationOfBook(evaluationOfBook);
        if (books.isEmpty()){
            return ResponseEntity.status(404).body(new GeneralResponse<>(
                    "Книг с данной оценкой не найдено"));
        }
        return ResponseEntity.ok().body(new GeneralResponse<>(books));
    }

}
