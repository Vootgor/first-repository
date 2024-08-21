package com.example.main.controller.only_for_books;

import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления книг */
@RestController
@RequestMapping("/library/books/delete")
public class ControllerBooksDeleted {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод для удаления книги по id
     * @param id id книги которую нужно удалить
     * @return возвращает строку с информацией о том какая книга была удалена
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> deleteBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        if (book == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>(
                    "Книги с данным id не найдено"));
        }
        serviceBook.deleteBook(id);
        return ResponseEntity.status(204).body(new GeneralResponse<>("Была удалена книга ", book));
    }

    /**
     * Метод удаляет все книги из базы. Не будет доступен для пользователя.
     * @return После удаления возвращает сообщение "Deleted all books"
     */
    @DeleteMapping("/allBooks")
    public String deletedAllBooks(){
        serviceBook.deleteAllBooks();
        return "Deleted all books";
    }

}
