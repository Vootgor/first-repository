package com.example.main.dao;

import com.example.main.entity.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBookFile extends JpaRepository<BookFile,Integer> {


}
