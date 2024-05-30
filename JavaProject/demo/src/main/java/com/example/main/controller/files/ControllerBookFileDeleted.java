package com.example.main.controller.files;

import com.example.main.entity.utilities.GeneralResponse;
import com.example.main.entity.BookFile;
import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления файлов */
@RestController
@RequestMapping("/library/bookFiles")
public class ControllerBookFileDeleted {

    @Autowired
    private ServiceBookFile serviceBookFile;

    /**
     * Метод для удаления файла по его id
     * @param id файла для удаления
     * @return выводит сообщение, что файл удалён
     */
    @DeleteMapping("/deleteBookFile/{id}")
    public ResponseEntity<GeneralResponse<?>> deleteBookFile(@PathVariable int id) {
        BookFile bookFile = serviceBookFile.getBookFile(id);
        if (bookFile == null){
            return ResponseEntity.status(404).body(new GeneralResponse<>("Файл с данным id не существует"));
        }
        serviceBookFile.deletedBookFile(id);
        return ResponseEntity.ok(new GeneralResponse<>("Был удалён файл ", bookFile));
    }



    /**
     * Метод для удаления всех файлов из базы. Не будет доступен для пользователя.
     * @return выводит сообщение, что все файлы были удалены
     */
    @DeleteMapping("/deleteAllBookFiles")
    public String deleteAllBookFiles() {
        serviceBookFile.deletedAllBookFiles();
        return "Были удалены все файлы";
    }



}
