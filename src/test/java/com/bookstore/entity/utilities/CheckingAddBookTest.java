package com.bookstore.entity.utilities;

import com.bookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckingAddBookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testCheckingTransmittedArgumentsForBook_TitleIsNull() {
        book.setTitleOfBook(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
        });
        assertEquals("Название книги не может быть null или пустым", exception.getMessage());
    }

    @Test
    void testCheckingTransmittedArgumentsForBook_TitleIsEmpty() {
        book.setTitleOfBook("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
        });
        assertEquals("Название книги не может быть null или пустым", exception.getMessage());
    }

    @Test
    void testCheckingTransmittedArgumentsForBook_TitleHasOnlyWhiteSpace() {
        book.setTitleOfBook("     ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
        });

        assertEquals("Название книги состоит только из пробелов или символов. " +
                "Давай переписывай", exception.getMessage());
    }

    @Test
    void testCheckingTransmittedArgumentsForBook_TitleHasOnlySymbols() {
        book.setTitleOfBook("!!!##$%$%@@@");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddBook.checkingTransmittedArgumentsForBook(book);
        });

        assertEquals("Название книги состоит только из пробелов или символов. " +
                "Давай переписывай", exception.getMessage());
    }


    @Test
    void testCheckingTransmittedArgumentsForBook_TitleLetterCase() {
        book.setTitleOfBook("bOoK");

        Book result = CheckingAddBook.checkingTransmittedArgumentsForBook(book);

        assertEquals("Book", result.getTitleOfBook());
    }

}
