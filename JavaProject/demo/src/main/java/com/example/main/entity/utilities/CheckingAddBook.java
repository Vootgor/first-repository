package com.example.main.entity.utilities;

import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;
import java.time.LocalDateTime;
import java.util.Set;

/** Утилитарный класс для проверки правильности написания полей
 * перед сохранением экземпляра класса Book в базу данных*/
public final class CheckingAddBook {

//    /**
//     * Метод принимает параметры для создания экземпляра класса Book.
//     * Проверяет что titleOfBook не null и не состоит из одних символов или пробелов, поднимает первую букву.
//     * @param titleOfBook строка содержащая название книги. Проверяется на null, обрезаются пробелы вначале
//     *                    и в конце, поднимается первая буква, затем проверяется если после удаления всех
//     *                    символов и пробелов строка пустая - выбрасывается exception.
//     * @param genre енам содержащий жанр
//     * @param quantityOfPage число содержащее кол-во страниц
//     * @param readingStatus енам содержащий статус чтения
//     * @param evaluationOfBook енам содержащий оценку
//     * @param commentOfBook строка содержащая комментарий
//     * @param bookAddedDate дата создания книги
//     * @param bookWasReadDate дата прочтения книги
//     * @param authors список авторов
//     * @param bookFiles список файлов
//     * @return возвращает новый экземпляр класса Book после всех проверок.
//     */


    public static Book checkingTransmittedArgumentsForBook(Book book) {

        String titleOfBook = book.getTitleOfBook();

        if (NullAndEmpty.stringIsNullOrEmpty(titleOfBook)){
            throw new IllegalArgumentException("Название книги не может быть null или пустым");
        }else {
            titleOfBook = titleOfBook.trim();
            //здесь добавил прямое сохранение в titleOfBook было без него
            titleOfBook = ElevateFirstLetter.raiseFirstLetter(titleOfBook);
            if (SymbolsAndWhitespace.removalSymbolsAndWhitespace(titleOfBook).isEmpty()){
                throw new IllegalArgumentException("Название книги состоит только из пробелов или символов. " +
                        "Давай переписывай");
            }
        }
        book.setTitleOfBook(titleOfBook);
        return book;
    }

}
