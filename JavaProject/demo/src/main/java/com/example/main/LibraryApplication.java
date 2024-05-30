package com.example.main;

import com.example.main.entity.Author;
import com.example.main.entity.utilities.CheckingAddAuthor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

//todo убрать лишний мейн

/*	public static void main(String[] args) {
		ArrayList<Author> authors = new ArrayList<>();
	}*/


}
