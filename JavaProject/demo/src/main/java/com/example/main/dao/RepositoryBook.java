package com.example.main.dao;

import com.example.main.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBook extends JpaRepository<Book,Integer> {
}
