package com.example.main.service;

import com.example.main.entity.BookFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface ServiceBookFile {


    List<BookFile> getAllBookFiles();


    List<BookFile> getBookFileById(Integer bookId);

    BookFile getBookFile(int id);


    void saveOrUpdate(BookFile bookFile);


    void deletedBookFile(int id);


    @Transactional
    void deletedBookFileForce(int id);

    void deletedAllBookFiles();

}
