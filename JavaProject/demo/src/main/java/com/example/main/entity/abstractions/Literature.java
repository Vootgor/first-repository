package com.example.main.entity.abstractions;

import com.example.main.entity.Author;
import com.example.main.entity.BookFile;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import jakarta.persistence.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity
public abstract class Literature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title_of_book")
    private String titleOfBook;

    @Column(name = "genre")
    private Genre genre;

    @Column(name = "quantity_of_page")
    private int quantityOfPage;

    @Column(name = "reading_status")
    private ReadingStatus readingStatus;

    @Column(name = "evaluation_of_book")
    private EvaluationOfBook evaluationOfBook;

    @Column(name = "comment_of_book")
    private String commentOfBook;

    @Column(name = "book_add_date")
    private LocalDateTime bookAddedDate;

    @Column(name = "book_was_read_date")
    private LocalDateTime bookWasReadDate;

    private BookFile bookFile;

    private Author author;

}
