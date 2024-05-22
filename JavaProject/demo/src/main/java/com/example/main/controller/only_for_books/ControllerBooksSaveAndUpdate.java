package com.example.main.controller.only_for_books;

import com.example.main.entity.Book;
import com.example.main.entity.utilities.CheckingAddBook;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для добавления и изменения книг */
@RestController
public class ControllerBooksSaveAndUpdate {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод служит для сохранения книги в базу. Перед сохранением проверяется состояние объекта с помощью метода
     * checkingTransmittedArgumentsForBook
     * @param book экземпляр класса Book
     * @return возвращаем новый экземпляр класса после всех проверок
     */
    @PostMapping("/library/books/add")
    public Book saveBook(@RequestBody Book book) {
        Book parseBook = CheckingAddBook.checkingTransmittedArgumentsForBook(
                book.getTitleOfBook()
                , book.getGenre()
                , book.getQuantityOfPage()
                , book.getReadingStatus()
                , book.getEvaluationOfBook()
                , book.getCommentOfBook()
                , book.getBookAddedDate()
                , book.getBookWasReadDate()
                , book.getAuthors()
                , book.getBookFiles()
        );

        serviceBook.saveOrUpdateBook(parseBook);
        return parseBook;
    }

    /**
     * Метод обновляет данные в существующей книги в базе
     * @param book переданный экземпляр клааса Book
     * @return воздвращает измененную книгу
     */
//todo сделать логику при изменении статуса на прочитанно проставлялась текущая дата в поле book_was_read_date
    @PutMapping("/library/books/update")
    public Book updateBook(@RequestBody Book book) {
        serviceBook.saveOrUpdateBook(book);
        return book;
    }

}
