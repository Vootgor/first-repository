package com.example.main.controller.only_for_file;

import com.example.main.service.ServiceBookFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Класс является контроллером содержащим методы для удаления файлов */
@RestController
public class ControllerBookFileDeleted {

    @Autowired
    private ServiceBookFile serviceBookFile;

    /**
     * Метод для удаления файла по его id
     * @param id файла для удаления
     * @return выводит сообщение что файл удалён
     */
    @DeleteMapping("/bookFile/{id}")
    public String deletedBookFile(@PathVariable int id) {
        serviceBookFile.deletedBookFile(id);
        return ("Удалён файл ");
    }

    /**
     * Метод для удаления всех файло из базы
     * @return выводит сообщение что все файлы были удалены
     */
    @DeleteMapping("/bookFile/all")
    public String deletedAllBookFiles() {
        serviceBookFile.deletedAllBookFiles();
        return "Были удалены все файлы";
    }

}
