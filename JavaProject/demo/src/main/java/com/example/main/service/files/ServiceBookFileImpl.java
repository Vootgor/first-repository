package com.example.main.service.files;

import com.example.main.dao.RepositoryBookFile;
import com.example.main.entity.BookFile;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceBookFileImpl implements ServiceBookFile {

    @Autowired
    RepositoryBookFile repositoryBookFile;



    @Override
    public List<BookFile> getAllBookFiles() {
        return repositoryBookFile.findAll();
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
    public void deletedAllBookFiles() {
        repositoryBookFile.deleteAll();
    }
}
