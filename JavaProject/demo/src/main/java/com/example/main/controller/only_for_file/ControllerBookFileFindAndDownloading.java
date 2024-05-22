package com.example.main.controller.only_for_file;

import com.example.main.entity.BookFile;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим метод для поиска файла по id и его загрузки */
@RestController
public class ControllerBookFileFindAndDownloading {

    @Autowired
    private ServiceBookFile serviceBookFile;

    /**
     * Метод для получения файла по id из базы который можно скачать
     * @param id файла который необходимо найти
     * @return данные для скачивания файла
     */
    @GetMapping("/bookFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        BookFile bookFile = serviceBookFile.getBookFile(id);
//        это можно будет раскоментить когда в базе не будет файлов без книг и книг без авторов
//        но все равно нужны будут проверки на null
//        System.out.println("Получили файл " + bookFile.getFileName() + "\nСвязан с книгой \""
//                + bookFile.getBook().getTitleOfBook() + "\"\nАвтор " + bookFile.getBook().getAuthors() );

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(bookFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getFileName() + "\"")
                .body(new ByteArrayResource(bookFile.getFileData()));
    }

}
