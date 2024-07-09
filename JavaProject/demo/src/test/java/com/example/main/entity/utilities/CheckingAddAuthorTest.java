package com.example.main.entity.utilities;

import com.example.main.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAddAuthorTest {

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
    }

    @Test
    void checkingTransmittedArgumentsForAuthor_NameIsNull() {
        author.setAuthorName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author);
        });
        assertEquals("Братюнь имя автора не может быть пустым или null. Сделай нормально"
                , exception.getMessage());
    }

    @Test
    void checkingTransmittedArgumentsForAuthor_NameIsEmpty() {
        author.setAuthorName("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author);
        });
        assertEquals("Братюнь имя автора не может быть пустым или null. Сделай нормально"
                , exception.getMessage());
    }

    @Test
    void checkingTransmittedArgumentsForAuthor_NameHasOnlyWhiteSpase(){
        author.setAuthorName("    ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author);
                });
        assertEquals("Братюнь имя автора не может быть пустым или состоять " +
                "только из символов и пробелов. Сделай нормально", exception.getMessage());
    }

    @Test
    void checkingTransmittedArgumentsForAuthor_NameHasOnlySymbols(){
        author.setAuthorName("!!@#$%^^$#");

        IllegalArgumentException exception = assertThrows()
    }


}
