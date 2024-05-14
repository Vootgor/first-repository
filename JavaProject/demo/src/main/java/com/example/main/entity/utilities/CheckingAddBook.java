package com.example.main.entity.utilities;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;

import java.time.LocalDateTime;
import java.util.Set;

public final class CheckingAddBook {

    public static Book checkingTransmittedArgumentsForBook(String titleOfBook, Genre genre, int quantityOfPage
            , ReadingStatus readingStatus, EvaluationOfBook evaluationOfBook, String commentOfBook
            , LocalDateTime bookAddedDate, LocalDateTime bookWasReadDate, Set<Author> authors
            , Set<BookFile> bookFiles) {

        System.out.println();
        System.out.println("-----------------Метод checkingTransmittedArgumentsForBook начал работу-------------------");
        if (NullAndEmpty.stringIsNullOrEmpty(titleOfBook)){
            throw new IllegalArgumentException("Название книги не может быть null или пустым");
        }else {
            titleOfBook = titleOfBook.trim();
            ElevateFirstLetter.raiseFirstLetter(titleOfBook);
            if (SymbolsAndWhitespace.removalSymbolsAndWhitespace(titleOfBook).isEmpty()){
                throw new IllegalArgumentException("Название книги состоит только из пробелов или символов." +
                        "Давай переписывай");
            }
        }
        System.out.println("Спарсили " + titleOfBook);

        Book book = new Book(titleOfBook, genre, quantityOfPage, readingStatus, evaluationOfBook, commentOfBook
                , bookAddedDate, bookWasReadDate, authors, bookFiles);
        System.out.println("-----------------Метод checkingTransmittedArgumentsForBook закончил работу-------------------");

        return book;
    }

}
