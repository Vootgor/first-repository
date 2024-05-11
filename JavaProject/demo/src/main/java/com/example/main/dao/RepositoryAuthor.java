package com.example.main.dao;

import com.example.main.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryAuthor extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId")
    List<Author> findAuthorsByBookId(int bookId);

    public List<Author> findByAuthorLastName(String authorLastName);

    List<Author> findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);

    public List<Author> findByAuthorNameAndAuthorLastNameAndAuthorPatronymic(String authorName, String authorLastName, String authorPatronymic);

    //ищет и возвращает список авторов по ФИ или ФИО если указанно отчетсво
    @Query("SELECT a FROM Author a WHERE " +
            "a.authorName = :authorName " +
            "AND a.authorLastName = :authorLastName " +
            "AND (:authorPatronymic IS NULL OR a.authorPatronymic = :authorPatronymic OR a.authorPatronymic = '')")
    public List<Author> findByAuthorFullName(String authorName, String authorLastName, String authorPatronymic);

    //проверка существования автора
    boolean existsByAuthorNameAndAuthorLastNameAndAuthorPatronymic(String authorName, String authorLastName, String authorPatronymic);

    // добавление связи между автором и книгой
    //todo Переделать nativeQuery = true на nativeQuery = false, переписать на JPQL(сейчас используется нативный SQL запрос)
    @Modifying
    @Query(value = "INSERT INTO books_authors (author_id, book_id) VALUES (:authorId, :bookId)", nativeQuery = true)
    void bindBookToAuthor(int bookId, int authorId);

}
