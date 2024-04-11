package com.example.main.entity.abstractions;


import com.example.main.entity.enam.EvaluationOfBook;
import com.example.main.entity.enam.Genre;
import com.example.main.entity.enam.ReadingStatus;
import jakarta.persistence.*;


import java.time.LocalDateTime;


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

    public Literature() {
    }

    public Literature(String titleOfBook, Genre genre, int quantityOfPage, ReadingStatus readingStatus,
                      EvaluationOfBook evaluationOfBook, String commentOfBook, LocalDateTime bookAddedDate,
                      LocalDateTime bookWasReadDate) {
        this.titleOfBook = titleOfBook;
        this.genre = genre;
        this.quantityOfPage = quantityOfPage;
        this.readingStatus = readingStatus;
        this.evaluationOfBook = evaluationOfBook;
        this.commentOfBook = commentOfBook;
        this.bookAddedDate = bookAddedDate;
        this.bookWasReadDate = bookWasReadDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getBookAddedDate() {
        return bookAddedDate;
    }

    public void setBookAddedDate(LocalDateTime bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    public LocalDateTime getBookWasReadDate() {
        return bookWasReadDate;
    }

    public void setBookWasReadDate(LocalDateTime bookWasReadDate) {
        this.bookWasReadDate = bookWasReadDate;
    }
}
