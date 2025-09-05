package com.bookstore.front;

import com.bookstore.controller.files.ControllerBookFileFindAndDownloading;
import com.bookstore.controller.only_for_books.ControllerBookFind;
import com.bookstore.controller.only_for_books.ControllerBooksDeleted;
import com.bookstore.controller.only_for_books.ControllerBooksSaveAndUpdate;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookFile;
import com.bookstore.entity.enums.EvaluationOfBook;
import com.bookstore.entity.enums.Genre;
import com.bookstore.entity.enums.ReadingStatus;
import com.bookstore.entity.utilities.DateFormatter;
import com.bookstore.service.ServiceBookFile;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Route("book")
public class BookView extends VerticalLayout {

    private Button deleteBookButton;
    private Button updateBookButton;
    private Button manageFileButton;


    private Grid<Book> grid;
    private ListDataProvider<Book> dataProvider;
    private TextField searchField;
    private ComboBox<Genre> searchGenre;
    private ComboBox<ReadingStatus> searchReadingStatus;

    private final ServiceBookFile serviceBookFile;
    private final ControllerBookFind controllerBookFind;
    private final ControllerBooksSaveAndUpdate controllerBooksSaveAndUpdate;
    private final ControllerBooksDeleted controllerBooksDeleted;
    private final ControllerBookFileFindAndDownloading controllerBookFileFindAndDownloading;


    public BookView(ServiceBookFile serviceBookFile, ControllerBookFind controllerBookFind, ControllerBooksSaveAndUpdate controllerBooksSaveAndUpdate, ControllerBooksDeleted controllerBooksDeleted, ControllerBookFileFindAndDownloading controllerBookFileFindAndDownloading) {
        this.serviceBookFile = serviceBookFile;
        this.controllerBookFind = controllerBookFind;
        this.controllerBooksSaveAndUpdate = controllerBooksSaveAndUpdate;
        this.controllerBooksDeleted = controllerBooksDeleted;
        this.controllerBookFileFindAndDownloading = controllerBookFileFindAndDownloading;
        createMainFields();
        prepareGrid();

        setAlignItems(Alignment.CENTER);
        Span labelTop = new Span("Книги");
        HorizontalLayout horizontalLayout = new HorizontalLayout(searchField, searchGenre, searchReadingStatus, updateBookButton, manageFileButton, deleteBookButton);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        HorizontalLayout horizontalLayout1 = new HorizontalLayout(labelTop, VaadinUtil.getMenuButton());
        horizontalLayout1.setAlignItems(Alignment.CENTER);
        add(horizontalLayout1, horizontalLayout, grid);

        setSizeFull();
        add(grid);
    }


    private void prepareGrid() {
        grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(Book::getTitleOfBook)
                .setHeader("Название")
                .setAutoWidth(true);
        grid
                .addColumn(new TextRenderer<>(book -> book.getGenre().getGenre()))
                .setKey("genre")
                .setHeader("Жанр")
                .setAutoWidth(true);
        grid.addColumn(Book::getQuantityOfPage)
                .setHeader("Кол-во страниц")
                .setAutoWidth(true);
        grid
                .addColumn(new TextRenderer<>(book -> book.getReadingStatus().getStatus()))
                .setKey("readingStatus")
                .setHeader("Статус чтения")
                .setAutoWidth(true);
        grid
                .addColumn(new TextRenderer<>(book -> book.getEvaluationOfBook().getEvaluation()))
                .setKey("evaluationOfBook")
                .setHeader("Оценка")
                .setAutoWidth(true);
        grid
                .addColumn(new TextRenderer<>(book -> DateFormatter.formatter.format(book.getBookAddedDate())))
                .setKey("bookAddedDate")
                .setHeader("Дата добавления")
                .setAutoWidth(true);
        grid
                .addColumn(new TextRenderer<>(book -> DateFormatter.formatter.format(book.getBookAddedDate())))
                .setKey("bookWasReadDate")
                .setHeader("Дата прочтения")
                .setAutoWidth(true);

        List<Book> data = Objects.requireNonNull(controllerBookFind.showAllBooks().getBody()).getData();
        dataProvider = new ListDataProvider<>(data);
        grid.setDataProvider(dataProvider);
    }

    private void createMainFields() {
        updateBookButton = new Button("Изменить книгу", __ -> {
            if (grid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери книгу", NotificationVariant.LUMO_ERROR);
                return;
            }
            updateDialog().open();
        });

        manageFileButton = new Button("Изменить файлы", __ -> {
            if (grid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери книгу", NotificationVariant.LUMO_ERROR);
                return;
            }
            createFilesDialog().open();
        });
        deleteBookButton = new Button("Удалить книгу", __ -> deleteBook());

        searchField = new TextField();
        searchField.setPlaceholder("Поиск имя, коммент.");
        searchField.addValueChangeListener(__ -> doFilter());

        searchGenre = new ComboBox<>();
        searchGenre.setItems(Genre.values());
        searchGenre.setItemLabelGenerator(Genre::getGenre);
        searchGenre.setPlaceholder("Жанр");
        searchGenre.addValueChangeListener(__ -> doFilter());

        searchReadingStatus = new ComboBox<>();
        searchReadingStatus.setItems(ReadingStatus.values());
        searchReadingStatus.setItemLabelGenerator(ReadingStatus::getStatus);
        searchReadingStatus.setPlaceholder("Статус чтения");
        searchReadingStatus.addValueChangeListener(__ -> doFilter());
    }

    private void doFilter() {
        dataProvider.setFilter(book -> {
            boolean title = StringUtils.containsIgnoreCase(book.getTitleOfBook(), searchField.getValue());
            boolean comment = StringUtils.containsIgnoreCase(book.getCommentOfBook(), searchField.getValue());
            boolean genreMatch = searchGenre.isEmpty() || book.getGenre().equals(searchGenre.getValue());
            boolean matchesPatronymic = searchReadingStatus.isEmpty() || book.getReadingStatus().equals(searchReadingStatus.getValue());
            return (title || comment) && genreMatch && matchesPatronymic;
        });
    }


    private void deleteBook() {
        Book book = grid.getSelectedItems().stream().findFirst().orElseThrow();
        var generalResponseResponseEntity = controllerBooksDeleted.deleteBook(book.getId());
        VaadinUtil.showOkNotOkNotification(generalResponseResponseEntity);
        recalcGrid();
    }

    private Dialog updateDialog() {
        var book = grid.getSelectedItems().stream().findFirst().orElseThrow();
        var title = new TextField("Название");
        title.setSizeFull();
        var quantityOfPages = new TextField("Кол-во страниц");
        quantityOfPages.setSizeFull();
        var genre = new ComboBox<Genre>("Жанр");
        genre.setItems(Genre.values());
        genre.setItemLabelGenerator(Genre::getGenre);
        genre.setSizeFull();
        var readingStatus = new ComboBox<ReadingStatus>("Статус чтения");
        readingStatus.setItems(ReadingStatus.values());
        readingStatus.setItemLabelGenerator(ReadingStatus::getStatus);
        readingStatus.setSizeFull();

        var evaluation = new ComboBox<EvaluationOfBook>("Оценка");
        evaluation.setItems(EvaluationOfBook.values());
        evaluation.setItemLabelGenerator(EvaluationOfBook::getEvaluation);
        evaluation.setSizeFull();

        var comment = new TextField("Коммент.");
        comment.setSizeFull();

        title.setValue(book.getTitleOfBook());
        quantityOfPages.setValue(String.valueOf(book.getQuantityOfPage()));
        genre.setValue(book.getGenre());
        readingStatus.setValue(book.getReadingStatus());
        evaluation.setValue(book.getEvaluationOfBook());
        comment.setValue(book.getCommentOfBook());


        var updateButton = new Button("Обновить", __ -> {
            book.setTitleOfBook(title.getValue());
            book.setQuantityOfPage(Integer.parseInt(quantityOfPages.getValue()));
            book.setGenre(genre.getValue());
            book.setReadingStatus(readingStatus.getValue());
            book.setEvaluationOfBook(evaluation.getValue());
            book.setCommentOfBook(comment.getValue());
            var generalResponseResponseEntity = controllerBooksSaveAndUpdate.updateBook(book);
            VaadinUtil.showOkNotOkNotification(generalResponseResponseEntity);
            recalcGrid();
        });

        VerticalLayout left = new VerticalLayout(title, genre, evaluation);
        VerticalLayout right = new VerticalLayout(readingStatus, quantityOfPages, comment);
        HorizontalLayout top = new HorizontalLayout(left, right);

        VerticalLayout main = new VerticalLayout(top, updateButton);
        main.setAlignSelf(Alignment.END, updateButton);
        Dialog dialog = new Dialog(main);
        dialog.setWidth("50%");
        VaadinUtil.attachCloseButton(dialog);
        return dialog;
    }

    private Dialog createFilesDialog() {
        var book = grid.getSelectedItems().stream().findFirst().orElseThrow();
        Set<BookFile> bookFiles = book.getBookFiles();
        var fileGrid = new Grid<BookFile>();
        fileGrid.setSizeFull();
        fileGrid.addColumn(BookFile::getId)
                .setHeader("Id")
                .setAutoWidth(true);
        fileGrid.addColumn(BookFile::getFileName)
                .setHeader("Название")
                .setAutoWidth(true);

        var downloadFile = new Button("Скачать файл", __ -> {
            if (fileGrid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери файл", NotificationVariant.LUMO_ERROR);
                return;
            }
            var file = fileGrid.getSelectedItems().stream().findFirst().orElseThrow();
            StreamResource resource = new StreamResource(file.getFileName(), () -> new ByteArrayInputStream(file.getFileData()));
            resource.setCacheTime(0);
            var anchor = new Anchor(resource, "");
            anchor.getElement().setAttribute("download", true);
            anchor.getStyle().set("display", "none");
            add(anchor);
            anchor.getElement().callJsFunction("click");
            anchor.addDetachListener(detach -> remove(anchor));
            fileGrid.setItems(serviceBookFile.getBookFileById(file.getBook().getId()));
        });

        var deleteFile = new Button("Удалить файл", __ -> {
            if (fileGrid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери файл", NotificationVariant.LUMO_ERROR);
                return;
            }
            var file = fileGrid.getSelectedItems().stream().findFirst().orElseThrow();
            System.out.println("try delete file id " + file.getId());
            serviceBookFile.deletedBookFileForce(file.getId());
            fileGrid.setItems(serviceBookFile.getBookFileById(file.getBook().getId()));
        });



        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(uploadEvent -> {
            saveFile(uploadEvent, buffer, book);
            fileGrid.setItems(book.getBookFiles());
        });
        fileGrid.setItems(bookFiles);



        Dialog dialog = new Dialog(upload,new HorizontalLayout(downloadFile, deleteFile), fileGrid);
        dialog.setWidth("80%");
        dialog.setHeight("100%");
        VaadinUtil.attachCloseButton(dialog);
        return dialog;
    }

    private void recalcGrid() {
        grid.setItems(Objects.requireNonNull(controllerBookFind.showAllBooks().getBody()).getData());
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


}
