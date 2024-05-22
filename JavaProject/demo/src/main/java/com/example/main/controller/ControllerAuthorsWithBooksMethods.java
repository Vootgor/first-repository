package com.example.main.controller;

import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Author;
import com.example.main.entity.Book;
import com.example.main.entity.utilities.CheckingAddAuthor;
import com.example.main.entity.utilities.CheckingAddBook;
import com.example.main.service.ServiceAuthor;
import com.example.main.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;


    /**
     * Метод из DTO создаёт экземпляры Author и Book. Входящие данные проходят через проверку на верное заполнение.
     * Затем происходит поиск совпадений в базе.
     * Если найдено более одного автора то выбрасывается exception, если
     * автор отсутствует, то он сохраняется в базу, если найден один автор, то он подставляется для последующей
     * связи с книгой.
     * Книга проверяется по названию и автору связанному с ней. Если есть совпадения то выбрасывается
     * exception.
     * @param bookAuthorAndBookDTO в параметрах передаются данные для последующего создания экземпляров классов
     *                             Author и Book.
     * @return возвращает сообщение о том какие автор и книга были добавлены.
     */
    //todo переписать извлечение автора из коллекции с помощью Stream
    //todo нужно сделать еще один метод проверки книги по всем полям кроме автора и файла.
    @PostMapping("/library/booksAndAuthors/add")
    public String saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorAndBookDTO) {

        System.out.println("приходит " + bookAuthorAndBookDTO);

        System.out.println("EVAL " + bookAuthorAndBookDTO.getEvaluationOfBook().toString());


        //проверка автора
        Author parseAuthor = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(bookAuthorAndBookDTO.getAuthorName()
                , bookAuthorAndBookDTO.getAuthorLastName(), bookAuthorAndBookDTO.getAuthorPatronymic());
        System.out.println("Спарсили " + parseAuthor);

        System.out.println("Ищем " + parseAuthor.getAuthorName() + " "
                + parseAuthor.getAuthorLastName() + " " + parseAuthor.getAuthorPatronymic());

        List<Author> otherAuthors;
        Author otherAuthor;

        if (serviceAuthor.existsByFIO(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                , parseAuthor.getAuthorPatronymic())) {
            otherAuthors = serviceAuthor.findByAuthorFullName(parseAuthor.getAuthorName()
                    , parseAuthor.getAuthorLastName(), parseAuthor.getAuthorPatronymic());
            try {
                if (otherAuthors.size() > 1) {
                    throw new IllegalArgumentException("Существует более одно автора с данным ФИО");
                } else {
                    otherAuthor = otherAuthors.get(0);
                }
            } finally {
                System.out.println("Найдены авторы");
                otherAuthors.forEach(System.out::println);
            }
        } else {
            otherAuthor = new Author(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                    , parseAuthor.getAuthorPatronymic());
            serviceAuthor.saveOrUpdateAuthor(otherAuthor);
        }


        //проверка книги
        Book parseBook = CheckingAddBook.checkingTransmittedArgumentsForBook(
                bookAuthorAndBookDTO.getTitleOfBook(),
                bookAuthorAndBookDTO.getGenre(),
                bookAuthorAndBookDTO.getQuantityOfPage(),
                bookAuthorAndBookDTO.getReadingStatus(),
                bookAuthorAndBookDTO.getEvaluationOfBook(),
                bookAuthorAndBookDTO.getCommentOfBook(),
                LocalDateTime.now(),
                null,Set.of(otherAuthor),
                null
        );

        if (serviceBook.existsByTitleOfBookAndAuthors(parseBook.getTitleOfBook(),otherAuthor)){
            serviceBook.findByTitleOfBook(parseBook.getTitleOfBook());
            throw new IllegalArgumentException("Данная книга уже существует " + parseBook + " с этим автором " + otherAuthor);
        }


        serviceBook.saveOrUpdateBook(parseBook);
        serviceAuthor.bindBookToAuthor(parseBook.getId(), otherAuthor.getId());
        return "Добавлена книга " + parseBook + "\nДобавлен автор " + otherAuthor;
    }


}
