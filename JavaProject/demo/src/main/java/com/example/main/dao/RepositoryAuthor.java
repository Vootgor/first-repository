package com.example.main.dao;

import com.example.main.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAuthor extends JpaRepository<Author,Integer> {
}
