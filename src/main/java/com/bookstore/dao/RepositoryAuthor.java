package com.bookstore.dao;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryAuthor extends JpaRepository<Author, Integer> {


    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId")
    List<Author> findAuthorsByBookId(int bookId);

    /**
     * Находит автора по фамилии
     * @param authorLastName фамилия
     * @return возвращает список авторов
     */
    public List<Author> findByAuthorLastName(String authorLastName);

    /**
     * Находит автора по имени и отчеству
     * @param authorName имя автора
     * @param authorLastName фамилия автора
     * @return возвращает список авторов
     */
    List<Author> findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);

    /**
     * Находит автора по ФИО
     * @param authorName имя автора
     * @param authorLastName фамилия автора
     * @param authorPatronymic отчество автора
     * @return возвращает список авторов
     */
    public List<Author> findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(String authorName, String authorLastName, String authorPatronymic);

    /**
     * ищет и возвращает список авторов по ФИ или ФИО если указанно отчетсво
     * @param authorName имя автора
     * @param authorLastName фамилия автора
     * @param authorPatronymic отчество автора (не обязательный параметр)
     * @return возвращает список авторов
     */
    @Query("SELECT a FROM Author a WHERE " +
            "a.authorName = :authorName " +
            "AND a.authorLastName = :authorLastName " +
            "AND (:authorPatronymic IS NULL OR a.authorPatronymic = :authorPatronymic OR a.authorPatronymic = '')")
    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic);


    /**
     * Проверяет существует ли автора
     * @param authorName имя автора
     * @param authorLastName фамилия автора
     * @param authorPatronymic отчество автора
     * @return возвращает true или false
     */
    boolean existsByAuthorNameAndAuthorLastNameAndAuthorPatronymic(String authorName, String authorLastName, String authorPatronymic);


    /**
     * Добавление связи между автором и книгой
     * @param bookId id книги
     * @param authorId id автора
     */
    //todo Переделать nativeQuery = true на nativeQuery = false, переписать на JPQL(сейчас используется нативный SQL запрос)
    @Modifying
    @Query(value = "INSERT INTO books_authors (author_id, book_id) VALUES (:authorId, :bookId)", nativeQuery = true)
    void bindBookToAuthor(int bookId, int authorId);

}
