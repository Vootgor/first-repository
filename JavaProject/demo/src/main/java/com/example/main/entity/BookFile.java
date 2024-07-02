package com.example.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Arrays;

/** Entity class*/
@Entity
@Table(name = "book_files")
public final class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @JsonIgnore
    @Column(name = "file_data")
    private byte [] fileData;

    @JsonIgnore
    //TODO удалил CascadeType.PERSIST
    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.REFRESH , CascadeType.DETACH})
    @JoinColumn(name = "book_id") // Связь с полем в таблице book_files
    private Book book;

    public BookFile() {
    }

    public BookFile(String fileName, long fileSize, String fileType, byte[] fileData, Book book) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileData = fileData;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    //todo ", fileData=" + Arrays.toString(fileData)
    // эту строку лучше убрать, т.к она сломает вывод из-за огромного кол-ва байтов
    @Override
    public String toString() {
        return "BookFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
