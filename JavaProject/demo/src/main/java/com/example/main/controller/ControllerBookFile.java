package com.example.main.controller;

import com.example.main.entity.BookFile;
import com.example.main.entity.utilities.GettingFileType;
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

@RestController
public class ControllerBookFile {

    @Autowired
    ServiceBookFile serviceBookFile;

    @GetMapping("bookFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id){
        BookFile bookFile = serviceBookFile.getBookFile(id);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(bookFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getFileName() + "\"")
                .body(new ByteArrayResource(bookFile.getFileData()));
    }


//Добавление файла
    @PostMapping("/bookFile")
    public String saveBookFile(@RequestParam("fileData") MultipartFile file){
        // Установка данных файла
        System.out.println("Зашли в обоссаный метод");
        BookFile bookFile = new BookFile();
        try {
            bookFile.setFileName(file.getOriginalFilename());
            bookFile.setFileSize(file.getSize());
            bookFile.setFileType(GettingFileType.gettingFileType(file.getOriginalFilename()));
            bookFile.setFileData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при чтении файла";
        }

        // Теперь можно сохранить объект bookFile в базу данных или произвести другие операции с ним
        serviceBookFile.saveOrUpdate(bookFile);

        return "Файл успешно загружен: " + file.getOriginalFilename();
    }

}
