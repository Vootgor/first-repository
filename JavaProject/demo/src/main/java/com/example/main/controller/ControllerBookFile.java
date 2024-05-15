package com.example.main.controller;

import com.example.main.entity.BookFile;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ControllerBookFile {

    @Autowired
    ServiceBookFile serviceBookFile;

    @PostMapping("/bookFile")
    public String saveBookFile(@RequestBody BookFile bookFile){
        serviceBookFile.saveOrUpdate(bookFile);
        return "Добавлен файл" + bookFile;
    }

    @PostMapping("/bookFile")
    public String saveBookFile(@ModelAttribute BookFile bookFile, @RequestParam("file") MultipartFile file){
        // Установка данных файла
        try {
            bookFile.setFileName(file.getOriginalFilename());
            bookFile.setFileSize(file.getSize());
            bookFile.setFileType(file.getContentType());
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
