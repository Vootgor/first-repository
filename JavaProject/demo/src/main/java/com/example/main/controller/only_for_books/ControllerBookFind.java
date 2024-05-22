package com.example.main.controller.only_for_books;

import com.example.main.dto.GeneralResponse;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Класс является контроллером содержащим методы для поиска книг */
@RestController
public class ControllerBookFind {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод возвращает список всех книг в базе
     * @return возвращает список всех книг в базе
     */
    @GetMapping("/books")
    public List<Book> showAllBooks(){
        return serviceBook.getAllBooks();
    }

    /**
     * Метод возвращает книгу по id или статус 404 если данной книги нет
     * @param id число являющееся id книги в базе
     * @return возвращает книгу по введёному id или статус 404 если данной книги нет
     */
    @GetMapping("/books/{id}")
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
    @GetMapping("/books/findByTitleOfBook")
    public List<Book> showBooksByTitleOfBook(@RequestParam String titleOfBook){
        return serviceBook.findByTitleOfBook(titleOfBook);
    }

    /**
     * Метод служит для поиска книги по жанру
     * @param genre жанр книги
     * @return возвращает список книг с данным жанром
     */
    @GetMapping("/books/genre/{genre}")
    public List<Book> showBooksByGenre(@PathVariable Genre genre){
        return serviceBook.findByGenre(genre);
    }

    /**
     * Метод служит для поиска книги по статусу чтения
     * @param readingStatus статус чтения
     * @return возвращает список книг с данным статусом
     */
    @GetMapping("/books/readingStatus/{readingStatus}")
    public List<Book> showBooksByReadingStatus(@PathVariable ReadingStatus readingStatus){
        return serviceBook.findByReadingStatus(readingStatus);
    }

    /**
     * Метод служит для поиска книги по её оценке
     * @param evaluationOfBook число являющееся оценкой книги
     * @return возвращает список книги с данной оценкой
     */
    @GetMapping("/books/evaluationOfBook/{evaluationOfBook}")
    public List<Book> showBooksByEvaluation(@PathVariable EvaluationOfBook evaluationOfBook){
        return serviceBook.findByEvaluationOfBook(evaluationOfBook);
    }



}
