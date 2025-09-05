package com.bookstore.controller;

import com.bookstore.dto.DtoAuthorsWithBooks;
import com.bookstore.entity.utilities.GeneralResponse;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.utilities.CheckingAddAuthor;
import com.bookstore.entity.utilities.CheckingAddBook;
import com.bookstore.service.ServiceAuthor;
import com.bookstore.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Класс является контроллером содержащим метод для одновременного добавления автора и книги.
 * А так же для последующей связи книги с автором
 */
@RestController
@RequestMapping("/library/booksAndAuthors")
public class ControllerAuthorsWithBooksMethods {

    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private ServiceAuthor serviceAuthor;


    /**
     * Метод из DTO создаёт экземпляры Author и Book. Входящие данные проходят через проверку на верное заполнение.
     * Затем происходит поиск совпадений в базе.
     * Если найдено более одного автора, то возвращаем 404 с сообщением, если
     * автор отсутствует, то он сохраняется в базу, если найден один автор, то он подставляется для последующей
     * связи с книгой.
     * Книга проверяется по названию и автору связанному с ней. Если есть совпадения, то возвращаем 404 с сообщением.
     *
     * @param bookAuthorAndBookDTO в параметрах передаются данные для последующего создания экземпляров классов
     *                             Author и Book.
     * @return возвращает 201 с сообщением о том какие автор и книга были добавлены.
     */
    @PostMapping("/add")
    // @Transactional для того чтоб метод выполнялся в рамках одной транзакции
    @Transactional
    public ResponseEntity<GeneralResponse<?>> saveBookAndAuthor(@RequestBody DtoAuthorsWithBooks bookAuthorAndBookDTO) {

        try {
            //проверка автора
            Author parseAuthor = new Author(bookAuthorAndBookDTO.getAuthorName()
                    , bookAuthorAndBookDTO.getAuthorLastName(), bookAuthorAndBookDTO.getAuthorPatronymic());
            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(parseAuthor);

            List<Author> otherAuthors;

            if (serviceAuthor.existsByFIO(parseAuthor.getAuthorName(), parseAuthor.getAuthorLastName()
                    , parseAuthor.getAuthorPatronymic())) {
                otherAuthors = serviceAuthor.findByAuthorFullName(parseAuthor.getAuthorName()
                        , parseAuthor.getAuthorLastName(), parseAuthor.getAuthorPatronymic());
                if (otherAuthors.size() > 1) {
                    return ResponseEntity.status(400).body(new GeneralResponse<>(
                            "Существует более одно автора с данным ФИО "
                            , otherAuthors));
                } else {
                    parseAuthor = otherAuthors.get(0);
                }
            } else {
                serviceAuthor.saveOrUpdateAuthor(parseAuthor);
            }

            //проверка книги
            Book parseBook = new Book(
                    bookAuthorAndBookDTO.getTitleOfBook(),
                    bookAuthorAndBookDTO.getGenre(),
                    bookAuthorAndBookDTO.getQuantityOfPage(),
                    bookAuthorAndBookDTO.getReadingStatus(),
                    bookAuthorAndBookDTO.getEvaluationOfBook(),
                    bookAuthorAndBookDTO.getCommentOfBook(),
                    LocalDateTime.now(),
                    null, Set.of(parseAuthor),
                    null);
            CheckingAddBook.checkingTransmittedArgumentsForBook(parseBook);

            if (serviceBook.existsByTitleOfBookAndAuthors(parseBook.getTitleOfBook(), parseAuthor)) {
                serviceBook.findByTitleOfBook(parseBook.getTitleOfBook());
                return ResponseEntity.status(400).body(new GeneralResponse<>(
                        "Данная книга с этим автором уже существует ", parseBook));
            }
            serviceBook.saveOrUpdateBook(parseBook);
            serviceAuthor.bindBookToAuthor(parseBook.getId(), parseAuthor.getId());

            return ResponseEntity.status(201).body(new GeneralResponse<>("Добавлена книга с автором: ", parseBook));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new GeneralResponse<>(e.getMessage()));
        }

    }


}
