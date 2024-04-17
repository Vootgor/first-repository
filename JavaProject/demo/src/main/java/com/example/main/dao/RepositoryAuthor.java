package com.example.main.dao;

import com.example.main.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryAuthor extends JpaRepository<Author,Integer> {

    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId")
    List<Author> findAuthorsByBookId(int bookId);

    Author findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);
}
