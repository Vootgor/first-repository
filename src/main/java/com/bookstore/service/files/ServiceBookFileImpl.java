package com.bookstore.service.files;

import com.bookstore.dao.RepositoryBookFile;
import com.bookstore.entity.BookFile;
import com.bookstore.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceBookFileImpl implements ServiceBookFile {

    @Autowired
    RepositoryBookFile repositoryBookFile;



    @Override
    public List<BookFile> getAllBookFiles() {
        return repositoryBookFile.findAll();
    }

    @Override
    public List<BookFile> getBookFileById(Integer bookId) {
        return repositoryBookFile.findByBook_Id(bookId);
    }


    @Override
    public BookFile getBookFile(int id) {
        return repositoryBookFile.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(BookFile bookFile) {
        repositoryBookFile.save(bookFile);
    }

    @Override
    public void deletedBookFile(int id) {
        repositoryBookFile.deleteById(id);
    }

    @Override
    @Transactional
    public void deletedBookFileForce(int id) {
        System.out.println("deleeting id  " + id);
        repositoryBookFile.deleteByIdForce(id);
    }


    @Override
    public void deletedAllBookFiles() {
        repositoryBookFile.deleteAll();
    }
}
