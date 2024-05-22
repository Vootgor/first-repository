package com.example.main.controller.only_for_author;

import com.example.main.entity.Author;
import com.example.main.entity.utilities.CheckingAddAuthor;
import com.example.main.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/** Класс является контроллером содержащим методы для сохранения и изменения авторов */
@RestController
public class ControllerAuthorSaveOrUpdate {

    @Autowired
    private ServiceAuthor serviceAuthor;

    /**
     * Метод добавляет нового автора в базу данных.
     * @param author экземпляр класса Author. Поля ФИО проверяются методом checkingTransmittedArgumentsForAuthor,
     *               проверяем есть ли такой автор, если есть то кидаем IllegalArgumentException.
     *               Иначе сохраняем автора в базу.
     * @return возвращает новый объект после всех проверок
     */
    @PostMapping("/add/authors")
    public Author saveAuthor(@RequestBody Author author) {
        Author otherAuthor = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(
                author.getAuthorName()
                , author.getAuthorLastName()
                , author.getAuthorPatronymic()
        );
        System.out.println("Спарсили " + otherAuthor);
        if (serviceAuthor.existsByFIO(otherAuthor.getAuthorName()
                , otherAuthor.getAuthorLastName(), otherAuthor.getAuthorPatronymic())) {
            throw new IllegalArgumentException("Данный автор уже существует, метод saveAuthor");
        } else
            serviceAuthor.saveOrUpdateAuthor(otherAuthor);

        return otherAuthor;
    }

    /**
     * Метод принимает List<Author> который проверяеся методом checkingTransmittedArgumentsForAuthor
     * и затем сохраняется в базу данных.
     * @param authors является List<Author> объекты которого перебираются и проверяются на правильность
     *               написания с помощью метода checkingTransmittedArgumentsForAuthor и затем
     *               добавляются в базу
     * @return возвращает размер List<Author> который сохранили в базу
     */
    @PostMapping("/authors/saveAllAuthors")
    public String saveAllAuthors(@RequestBody List<Author> authors) {
        List<Author> otherAuthors = authors.stream()
                .map(author -> CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(
                        author.getAuthorName(),
                        author.getAuthorLastName(),
                        author.getAuthorPatronymic()))
                .collect(Collectors.toList());

        otherAuthors.forEach(System.out::println);
        serviceAuthor.saveAllAuthors(otherAuthors);

        return "Добавленно " + authors.size();
    }

    /**
     * Метод для обновления автора
     * @param author экзепляр класса Author
     * @return возвращает измененного автора
     */
    //todo проверок вообще никаких нет, а должны быть. Надо будет добавить.
    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody Author author) {
        serviceAuthor.saveOrUpdateAuthor(author);
        return author;
    }


}
