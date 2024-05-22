package com.example.main.entity.utilities;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import java.time.LocalDateTime;
import java.util.Set;

/** Утилитарный класс для проверки правильности написания полей
 * перед сохранением экземпляра класса Book в базу данных*/
public final class CheckingAddBook {

    /**
     * Метод принимает параметры для создания экземпляра класса Book.
     * Проверяет что titleOfBook не null и не состоит из одних символов или пробелов, поднимает первую букву.
     * @param titleOfBook строка содержащая название книги. Проверяется на null, обрезаются пробелы вначале
     *                    и в конце, поднимается первая буква, затем проверяется если после удаления всех
     *                    символов и пробелов строка пустая - выбрасывается exception.
     * @param genre енам содержащий жанр
     * @param quantityOfPage число содержащее кол-во страниц
     * @param readingStatus енам содержащий статус чтения
     * @param evaluationOfBook енам содержащий оценку
     * @param commentOfBook строка содержащая комментарий
     * @param bookAddedDate дата создания книги
     * @param bookWasReadDate дата прочтения книги
     * @param authors список авторов
     * @param bookFiles список файлов
     * @return возвращает новый экземпляр класса Book после всех проверок.
     */
    //todo Возможно стоит переделать чтоб в параметры метода принимался экзепляр класса и работать уже
    //с его полями не создавая новый экземпляр.
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
