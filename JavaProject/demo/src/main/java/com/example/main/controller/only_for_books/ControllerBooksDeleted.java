package com.example.main.controller.only_for_books;

import com.example.main.entity.Book;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления книг */
@RestController
public class ControllerBooksDeleted {

    @Autowired
    private ServiceBook serviceBook;

    /**
     * Метод для удаления книги по id
     * @param id id книги которую нужно удалить
     * @return возвращает строку с информацией о том какая книга была удалена
     */
    @DeleteMapping("/library/books/delete/{id}")
    public String deleteBook(@PathVariable int id){
        Book book = serviceBook.getBook(id);
        serviceBook.deleteBook(id);
        return "Deleted book " + book + " with id " + id;
    }

    /**
     * Метод удаляет все книги из базы
     * @return После удаления возвращает сообщение "Deleted all books"
     */
    @DeleteMapping("/library/books/delete/allBooks")
    public String deletedAllBooks(){
        serviceBook.deleteAllBooks();
        return "Deleted all books";
    }

}
