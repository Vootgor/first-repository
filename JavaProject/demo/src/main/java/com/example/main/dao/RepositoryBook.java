package com.example.main.dao;

import com.example.main.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryBook extends JpaRepository<Book,Integer> {

    List<Book> findByTitleOfBook(String titleOfBook);
}
