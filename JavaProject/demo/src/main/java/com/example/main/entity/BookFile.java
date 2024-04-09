package com.example.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_files")
public class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_size")
    long fileSize;

    @Column(name = "file_type")
    String fileType;

    @Column(name = "file_data")
    byte [] fileData;

}
