package com.example.main.controller;

import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.utilities.GettingFileType;
import com.example.main.service.ServiceBook;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class ControllerBookFile {

    @Autowired
    ServiceBookFile serviceBookFile;

    @Autowired
    ServiceBook serviceBook;

    //получение файла
    @GetMapping("bookFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        BookFile bookFile = serviceBookFile.getBookFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(bookFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getFileName() + "\"")
                .body(new ByteArrayResource(bookFile.getFileData()));
    }


    //Добавление файла
    @PostMapping("/bookFile")
    public String saveBookFile(@RequestParam(required = false) Integer bookId, @RequestParam("fileData") MultipartFile file) {
        // Установка данных файла
        System.out.println("Зашли в метод saveBookFile");
        BookFile bookFile = new BookFile();
        Book book;

        if (file.getSize() == 0) {
            throw new IllegalArgumentException("Файл не может быть пустым");
        } else {
            try {
                bookFile.setFileName(file.getOriginalFilename());
                bookFile.setFileSize(file.getSize());
//это у нас вытаскивало абревиатуру формата в отдельное поле file_name в базе данных, надо подумать как лучше реализовать
//но чтоб это работало надо менять MediaType.parseMediaType на что-то другое
//                bookFile.setFileType(GettingFileType.gettingFileType(Objects.requireNonNull(file.getOriginalFilename()
//                        , "Имя файла не может быть пустым контроллер saveBookFile")));
                bookFile.setFileType(file.getContentType());
                bookFile.setFileData(file.getBytes());

                if (bookId != null){
                    book =  serviceBook.getBook(bookId);
                    bookFile.setBook(book);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Ошибка при чтении файла";
            }
        }

        // Теперь можно сохранить объект bookFile в базу данных или произвести другие операции с ним
        serviceBookFile.saveOrUpdate(bookFile);

        return "Файл успешно загружен: " + file.getOriginalFilename();
    }

    //Удалить файл по id
    @DeleteMapping("/bookFile/{id}")
    public String deletedBookFile(@PathVariable int id) {
        serviceBookFile.deletedBookFile(id);
        return ("Удалён файл ");
    }

    //Удалить все файлы
    @DeleteMapping("/bookFile/all")
    public String deletedAllBookFiles() {
        serviceBookFile.deletedAllBookFiles();
        return "Были удалены все файлы";
    }


}
