package com.bookstore.dao;

import com.bookstore.entity.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBookFile extends JpaRepository<BookFile,Integer> {


    List<BookFile> findByBook_Id(int id);

    @Modifying
    @Query("DELETE FROM BookFile bf WHERE bf.id = :id")
    void deleteByIdForce(int id);
}
