package com.example.main.front;

import com.example.main.controller.ControllerAuthorsWithBooksMethods;
import com.example.main.dto.DtoAuthorsWithBooks;
import com.example.main.entity.Book;
import com.example.main.entity.BookFile;
import com.example.main.entity.enums.EvaluationOfBook;
import com.example.main.entity.enums.Genre;
import com.example.main.entity.enums.ReadingStatus;
import com.example.main.service.ServiceBook;
import com.example.main.service.ServiceBookFile;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.util.Objects;

import static com.example.main.front.TextConstants.*;

@Route("addBook")
public class AddBookVIew extends VerticalLayout {

    private final ControllerAuthorsWithBooksMethods controllerAuthorsWithBooksMethods;
    private final ServiceBookFile serviceBookFile;

    private final ServiceBook serviceBook;

    private TextField lastNameT;
    private TextField firstNameT;
    private TextField patronymicT;

    private TextField bookNameT;
    private ComboBox<Genre> bookGenreT;
    private TextField bookQuantityPageT;
    private ComboBox<ReadingStatus> bookReadingStatusBox;
    private ComboBox<EvaluationOfBook> bookEvaluationBox;
    private TextField bookCommentT;

    private Button submitButton;

    public AddBookVIew(ControllerAuthorsWithBooksMethods controllerAuthorsWithBooksMethods, ServiceBookFile serviceBookFile, ServiceBook serviceBook) {
        this.controllerAuthorsWithBooksMethods = controllerAuthorsWithBooksMethods;
        this.serviceBookFile = serviceBookFile;
        this.serviceBook = serviceBook;
        createFieldsForAuthor();
        createFieldsForBook();
        createSubmitButton();

        setAlignItems(Alignment.CENTER);

        VerticalLayout authorSide = new VerticalLayout(lastNameT, firstNameT, patronymicT);
        VerticalLayout bookSide = new VerticalLayout(
                bookNameT, bookGenreT, bookQuantityPageT,
                bookReadingStatusBox, bookEvaluationBox, bookCommentT
        );


        HorizontalLayout mainLayout = new HorizontalLayout(authorSide, bookSide);
        Span labelTop = new Span(ADD_NEW_BOOK);
        HorizontalLayout horizontalLayout = new HorizontalLayout(labelTop, VaadinUtil.toMenu);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        add(horizontalLayout, mainLayout, submitButton);
        setAlignSelf(Alignment.END, submitButton);

    }

    private void createFieldsForAuthor() {
        lastNameT = new TextField(LAST_NAME);
        firstNameT = new TextField(FIRST_NAME);
        patronymicT = new TextField(PATRONYMIC_NAME);
    }

    private void createFieldsForBook() {
        bookNameT = new TextField(BOOK_NAME);

        bookGenreT = new ComboBox<>(BOOK_GENRE);
        bookGenreT.setItems(Genre.values());
        bookGenreT.setItemLabelGenerator(Genre::getGenre);

        bookQuantityPageT = new TextField(BOOK_QUANTITY_PAGE);

        bookReadingStatusBox = new ComboBox<>(BOOK_READING_STATUS);
        bookReadingStatusBox.setItems(ReadingStatus.values());
        bookReadingStatusBox.setItemLabelGenerator(ReadingStatus::getStatus);

        bookEvaluationBox = new ComboBox<>(BOOK_EVALUATION);
        bookEvaluationBox.setItems(EvaluationOfBook.values());
        bookEvaluationBox.setItemLabelGenerator(EvaluationOfBook::getEvaluation);

        bookCommentT = new TextField(BOOK_COMMENT);
    }

    private void createSubmitButton() {
        submitButton = new Button(TextConstants.ADD,
                __ -> {
                    if (!isFieldsValidated()) {
                        VaadinUtil.showTopDefaultNotification("Ошибка", NotificationVariant.LUMO_ERROR);
                        return;
                    }
                    var bookAndAuthorRequest = new DtoAuthorsWithBooks(
                            bookNameT.getValue(),
                            bookGenreT.getValue(),
                            bookQuantityPageT.getValue().isEmpty() ? 0 : Integer.parseInt(bookQuantityPageT.getValue()),
                            bookReadingStatusBox.getValue(),
                            bookEvaluationBox.getValue(),
                            bookCommentT.getValue(),
                            firstNameT.getValue(),
                            lastNameT.getValue(),
                            patronymicT.getValue()

                    );
                    var creationResult =
                            controllerAuthorsWithBooksMethods.saveBookAndAuthor(bookAndAuthorRequest);
                    //TODO добавить более внятный ответ
                    if (creationResult.getStatusCode().is2xxSuccessful()) {
                        VaadinUtil.showTopDefaultNotification("Все ок", NotificationVariant.LUMO_SUCCESS);
                        VaadinUtil.yesNoDialog(
                                "Книга добавлена, загрузить файл?",
                                event -> {
                                    Book savedBook = (Book) Objects.requireNonNull(creationResult.getBody()).getData();
                                    MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
                                    Upload upload = new Upload(buffer);
                                    upload.addSucceededListener(uploadEvent -> saveFile(uploadEvent, buffer, savedBook));
                                    Dialog uploadDialog = new Dialog(upload);
                                    uploadDialog.open();
                                }
                        ).open();
                    } else {
                        VaadinUtil.showTopDefaultNotification("Неок", NotificationVariant.LUMO_ERROR);
                    }
                });
    }

    private void saveFile(SucceededEvent uploadEvent, MultiFileMemoryBuffer buffer, Book savedBook) {
        try {
            var bytes = buffer.getInputStream(uploadEvent.getFileName()).readAllBytes();
            serviceBookFile.saveOrUpdate(new BookFile(
                    uploadEvent.getFileName(),
                    bytes.length,
                    uploadEvent.getMIMEType(),
                    bytes,
                    savedBook
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFieldsValidated() {
        return true;
    }


}
