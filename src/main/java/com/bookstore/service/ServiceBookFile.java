package com.bookstore.service;

import com.bookstore.entity.BookFile;
import org.springframework.transaction.annotation.Transactional;

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
