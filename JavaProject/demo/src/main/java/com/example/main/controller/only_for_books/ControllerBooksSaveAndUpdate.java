package com.example.main.controller.only_for_books;

import com.example.main.entity.Book;
import com.example.main.entity.utilities.CheckingAddBook;
import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Класс является контроллером содержащим методы для добавления и изменения книг */
@RestController
@RequestMapping("/library/books/saveOrUpdate")
public class ControllerBooksSaveAndUpdate {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод служит для сохранения книги в базу. Перед сохранением проверяется состояние объекта с помощью метода
     * checkingTransmittedArgumentsForBook
     * @param book экземпляр класса Book
     * @return возвращаем новый экземпляр класса после всех проверок
     */
    @PostMapping("/saveBook")
    public ResponseEntity<GeneralResponse<?>> saveBook(@RequestBody Book book) {
        try {
            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
            serviceBook.saveOrUpdateBook(book);
            return ResponseEntity.status(201).body(new GeneralResponse<>(
                    "Добавлена новая книга ", book));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new GeneralResponse<>(e.getMessage()));
        }
    }

    /**
     * Метод обновляет данные в существующей книги в базе
     * @param book переданный экземпляр класса Book
     * @return воздвращает измененную книгу
     */
//todo сделать логику при изменении статуса на прочитано проставлялась текущая дата в поле book_was_read_date
    @PutMapping("/updateBook")
    public ResponseEntity<GeneralResponse<Book>> updateBook(@RequestBody Book book) {
        try {
            System.out.println("Получен id книги = " + book.getId());
            if (book.getId() == 0){
                return ResponseEntity.status(400).body(new GeneralResponse<>("Не указан id книги"));
            } else if (!serviceBook.existById(book.getId())) {
                return ResponseEntity.status(404).body(new GeneralResponse<>("Книга с данным id не найдена"));
            }

            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
            serviceBook.saveOrUpdateBook(book);
            return ResponseEntity.ok().body(new GeneralResponse<>("Данные книги были обновлены ", book));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new GeneralResponse<>(e.getMessage()));
        }
    }
}
