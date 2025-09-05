package com.bookstore.entity.abstractions;


import com.bookstore.entity.enums.EvaluationOfBook;
import com.bookstore.entity.enums.Genre;
import com.bookstore.entity.enums.ReadingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


@MappedSuperclass
public abstract class Literature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title_of_book")
    private String titleOfBook;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre = Genre.UNKNOWN;

    @Column(name = "quantity_of_page")
    private int quantityOfPage;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_status")
    private ReadingStatus readingStatus= ReadingStatus.UNKNOWN;

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_of_book")
    private EvaluationOfBook evaluationOfBook = EvaluationOfBook.NO_GRADES;

    @Column(name = "comment_of_book")
    private String commentOfBook;

    @Column(name = "book_add_date")
    private LocalDateTime bookAddedDate = LocalDateTime.now();

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

    @Override
    public String toString() {
        return "Literature{" +
                "id=" + id +
                ", titleOfBook='" + titleOfBook + '\'' +
                ", genre=" + genre +
                ", quantityOfPage=" + quantityOfPage +
                ", readingStatus=" + readingStatus +
                ", evaluationOfBook=" + evaluationOfBook +
                ", commentOfBook='" + commentOfBook + '\'' +
                ", bookAddedDate=" + bookAddedDate +
                ", bookWasReadDate=" + bookWasReadDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Literature that)) return false;
        return id == that.id && quantityOfPage == that.quantityOfPage && Objects.equals(titleOfBook, that.titleOfBook) && genre == that.genre && readingStatus == that.readingStatus && evaluationOfBook == that.evaluationOfBook && Objects.equals(commentOfBook, that.commentOfBook) && Objects.equals(bookAddedDate, that.bookAddedDate) && Objects.equals(bookWasReadDate, that.bookWasReadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleOfBook, genre, quantityOfPage, readingStatus, evaluationOfBook, commentOfBook, bookAddedDate, bookWasReadDate);
    }
}
