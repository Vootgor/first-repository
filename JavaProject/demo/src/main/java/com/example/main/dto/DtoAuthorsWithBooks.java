package com.example.main.dto;


import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;

/**
 * DTO класс содержащий общие поля для создания экземпляров классов Author и Book
 */
public class DtoAuthorsWithBooks {

    private String titleOfBook;
    private Genre genre;
    private int quantityOfPage;
    private ReadingStatus readingStatus;
    private EvaluationOfBook evaluationOfBook;
    private String commentOfBook;
    private String authorName;
    private String authorLastName;
    private String authorPatronymic;

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getQuantityOfPage() {
        return quantityOfPage;
    }

    public void setQuantityOfPage(int quantityOfPage) {
        this.quantityOfPage = quantityOfPage;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    public EvaluationOfBook getEvaluationOfBook() {
        return evaluationOfBook;
    }

    public void setEvaluationOfBook(EvaluationOfBook evaluationOfBook) {
        this.evaluationOfBook = evaluationOfBook;
    }

    public String getCommentOfBook() {
        return commentOfBook;
    }

    public void setCommentOfBook(String commentOfBook) {
        this.commentOfBook = commentOfBook;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public void setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
    }
}
