package com.example.main.entity.abstractions;

import com.example.main.entity.Author;
import com.example.main.entity.BookFile;
import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public abstract class Literature {

    NamedParameterJdbcTemplate jdbcTemplate;

    int id;

    String titleOfBook;

    Genre genre;

    int quantityOfPage;

    ReadingStatus readingStatus;

    EvaluationOfBook evaluationOfBook;

    String commentOfBook;

    LocalDateTime bookAddedDate;

    LocalDateTime bookWasReadDate;

    Map<Integer, BookFile> fileMetadata;

    Map<Integer, Author> authorMetadata;


}
