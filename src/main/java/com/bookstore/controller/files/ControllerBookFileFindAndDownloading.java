package com.bookstore.controller.files;

import com.bookstore.entity.BookFile;
import com.bookstore.entity.utilities.GeneralResponse;
import com.bookstore.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс является контроллером содержащим метод для поиска файла по id и его загрузки
 */
@RestController
@RequestMapping("/library/bookFiles")
public class ControllerBookFileFindAndDownloading {

    @Autowired
    private ServiceBookFile serviceBookFile;

    /**
     * Метод для получения файла по id из базы который можно скачать
     *
     * @param id файла который необходимо найти
     * @return данные для скачивания файла
     */
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable int id) {
        BookFile bookFile = serviceBookFile.getBookFile(id);

        if (bookFile == null) {
            return ResponseEntity.status(404).body(new GeneralResponse<>("Файл с данным id не найден"));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(bookFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getFileName() + "\"")
                .body(new ByteArrayResource(bookFile.getFileData()));
    }

}
