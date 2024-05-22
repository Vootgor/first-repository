package com.example.main.controller.only_for_file;

import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
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

/** Класс является контроллером содержащим метод для добавления файла и привяки его к книге */
@RestController
public class ControllerBookFile {

    @Autowired
    private ServiceBookFile serviceBookFile;

    @Autowired
    private ServiceBook serviceBook;


    /**
     * Метод служит для добавления нового файла в базу и привязки его к книге в базе. Перед сохранением
     * файл проверяется что он не пуст, если пуст кидает IllegalArgumentException.
     * @param bookId id книги с которой нужно связать файл
     * @param file загружаемый файл
     * @return возвращает строку о том что файл загружен и имя файла
     */
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


}
