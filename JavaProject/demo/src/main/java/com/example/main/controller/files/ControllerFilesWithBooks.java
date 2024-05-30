package com.example.main.controller.files;

import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.service.ServiceBook;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Класс является контроллером содержащим метод для добавления файла и привязки его к книге
 */
@RestController
@RequestMapping("/library/bookFiles")
public class ControllerFilesWithBooks {

    @Autowired
    private ServiceBookFile serviceBookFile;

    @Autowired
    private ServiceBook serviceBook;


    /**
     * Метод служит для добавления нового файла в базу и привязки его к книге в базе. Перед сохранением
     * файл проверяется что он не пуст, если пуст кидает IllegalArgumentException.
     *
     * @param bookId id книги с которой нужно связать файл
     * @param file   загружаемый файл
     * @return возвращает строку о том что файл загружен и имя файла
     */
    //todo нужна проверка чтоб не добавлялся один и тот же файл
    @PostMapping("/saveBookFile")
    public ResponseEntity<GeneralResponse<?>> saveBookFile(@RequestParam(required = false) Integer bookId, @RequestParam("fileData") MultipartFile file) {
        // Установка данных файла
        System.out.println("Зашли в метод saveBookFile");
        BookFile bookFile = new BookFile();

        if (file.getSize() == 0) {
            return ResponseEntity.status(400).body(new GeneralResponse<>("Файл не может быть пустым"));
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

                if (bookId == null) {
                    return ResponseEntity.status(404).body(new GeneralResponse<>("Не указан id книги"));
                } else {
                    Book book = serviceBook.getBook(bookId);
                    if (book == null) {
                        return ResponseEntity.status(404).body(new GeneralResponse<>("Книга с данным id не найдена"));
                    }
                    bookFile.setBook(book);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(400).body(new GeneralResponse<>("Ошибка при чтении файла"));
            }
        }

        serviceBookFile.saveOrUpdate(bookFile);
        return ResponseEntity.status(201).body(new GeneralResponse<>(
                "Файл успешно загружен: ", file.getOriginalFilename()));
    }


}
