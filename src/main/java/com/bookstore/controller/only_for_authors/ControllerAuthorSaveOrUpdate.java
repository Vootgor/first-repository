package com.bookstore.controller.only_for_authors;

import com.bookstore.entity.utilities.GeneralResponse;
import com.bookstore.entity.Author;
import com.bookstore.entity.utilities.CheckingAddAuthor;
import com.bookstore.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс является контроллером содержащим методы для сохранения и изменения авторов
 */
@RestController
@RequestMapping("/library/authors/saveOrUpdate")
public class ControllerAuthorSaveOrUpdate {

    @Autowired
    private ServiceAuthor serviceAuthor;


    /**
     * Метод добавляет нового автора в базу данных.
     *
     * @param author Экземпляр класса Author. Поля ФИО проверяются методом checkingTransmittedArgumentsForAuthor,
     *               проверяем есть ли такой автор, если есть то возвращаем статус 404 с сообщением.
     *               Иначе сохраняем автора в базу.
     * @return возвращает новый объект после всех проверок
     */
    @PostMapping("/saveAuthor")
    public ResponseEntity<GeneralResponse<Author>> saveAuthor(@RequestBody Author author) {
        try {
            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author);
            System.out.println("Спарсили " + author);
            if (serviceAuthor.existsByFIO(author.getAuthorName()
                    , author.getAuthorLastName(), author.getAuthorPatronymic())) {
                return ResponseEntity.status(400).body(new GeneralResponse<>(
                        "Данный автор уже существует, метод saveAuthor"));
            } else
                serviceAuthor.saveOrUpdateAuthor(author);

            return ResponseEntity.status(201).body(new GeneralResponse<>(author));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new GeneralResponse<>(e.getMessage()));
        }
    }


    /**
     * Метод принимает List<Author> который проверяется методом checkingTransmittedArgumentsForAuthor
     * и затем сохраняется в базу данных.
     *
     * @param authors является List<Author> объекты которого перебираются и проверяются на правильность
     *                написания с помощью метода checkingTransmittedArgumentsForAuthor и затем
     *                добавляются в базу
     * @return возвращает размер List<Author> который сохранили в базу
     */
    @PostMapping("/saveAllAuthors")
    public ResponseEntity<GeneralResponse<String>> saveAllAuthors(@RequestBody List<Author> authors) {
        try {
            List<Author> otherAuthors = authors.stream()
                    .map(CheckingAddAuthor::checkingTransmittedArgumentsForAuthor)
                    .collect(Collectors.toList());

            otherAuthors.forEach(System.out::println);
            serviceAuthor.saveAllAuthors(otherAuthors);

            return ResponseEntity.status(201).body(new GeneralResponse<>("Добавлено " + authors.size() + " авторов"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GeneralResponse<>(e.getMessage()));
        }
    }


    /**
     * Метод для обновления автора
     *
     * @param author экземпляр класса Author
     * @return возвращает измененного автора
     */
    //todo при введении не существующего id создаёт нового автора, исправить
    @PutMapping("/updateAuthor")
    public ResponseEntity<GeneralResponse<?>> updateAuthor(@RequestBody Author author) {

        try {
            System.out.println("Получен автор " + author);
            if (author.getId() == 0) {
                return ResponseEntity.status(400).body(new GeneralResponse<>("Не указан id автора"));
            } else if (!serviceAuthor.existsById(author.getId())) {
                return ResponseEntity.status(404).body(new GeneralResponse<>("Автор с данный id не найден"));
            }

            CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author);
            if (serviceAuthor.existsByFIO(author.getAuthorName(),
                    author.getAuthorLastName(),
                    author.getAuthorPatronymic())) {
                List<Author> otherAuthors = serviceAuthor.findByAuthorFullName(
                        author.getAuthorName(),
                        author.getAuthorLastName(),
                        author.getAuthorPatronymic());
                return ResponseEntity.status(400).body(new GeneralResponse<>(
                        "Данный автор уже существует", otherAuthors));
            }
            serviceAuthor.saveOrUpdateAuthor(author);
            return ResponseEntity.ok(new GeneralResponse<>("Данные автора были обновлены.", author));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GeneralResponse<>(e.getMessage()));
        }
    }
}
