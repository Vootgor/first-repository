package com.example.main.front;

import com.example.main.controller.only_for_authors.ControllerAuthorDeleted;
import com.example.main.controller.only_for_authors.ControllerAuthorFind;
import com.example.main.controller.only_for_authors.ControllerAuthorSaveOrUpdate;
import com.example.main.entity.Author;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Route("author")
public class AuthorView extends VerticalLayout {

    private final ControllerAuthorSaveOrUpdate controllerAuthorSaveOrUpdate;
    private final ControllerAuthorFind controllerAuthorFind;
    private final ControllerAuthorDeleted controllerAuthorDeleted;


    private Button addAuthor;
    private Button deleteAuthor;
    private Button updateAuthor;

    private Grid<Author> grid;
    private ListDataProvider<Author> dataProvider;
    private TextField searchField;

    public AuthorView(ControllerAuthorSaveOrUpdate controllerAuthorSaveOrUpdate, ControllerAuthorFind controllerAuthorFind, ControllerAuthorDeleted controllerAuthorDeleted) {
        this.controllerAuthorSaveOrUpdate = controllerAuthorSaveOrUpdate;
        this.controllerAuthorFind = controllerAuthorFind;
        this.controllerAuthorDeleted = controllerAuthorDeleted;
        createMainFields();
        prepareGrid();

        setAlignItems(Alignment.CENTER);
        Span labelTop = new Span("Авторы");
        HorizontalLayout horizontalLayout = new HorizontalLayout(searchField, addAuthor, deleteAuthor, updateAuthor);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        HorizontalLayout horizontalLayout1 = new HorizontalLayout(labelTop, VaadinUtil.toMenu);
        horizontalLayout1.setAlignItems(Alignment.CENTER);
        add(horizontalLayout1, horizontalLayout, grid);


    }

    private void prepareGrid() {
        grid = new Grid<>();
        grid.addColumn(Author::getAuthorLastName)
                .setHeader("Фамилия")
                .setAutoWidth(true);
        grid.addColumn(Author::getAuthorName)
                .setHeader("Имя")
                .setAutoWidth(true);
        grid.addColumn(Author::getAuthorPatronymic)
                .setHeader("Отчество")
                .setAutoWidth(true);
        List<Author> data = Objects.requireNonNull(controllerAuthorFind.showAllAuthors().getBody()).getData();
        dataProvider = new ListDataProvider<>(data);
        grid.setDataProvider(dataProvider);
    }

    private void createMainFields() {
        addAuthor = new Button("Добавить автора", __ -> createAddDialog().open());
        deleteAuthor = new Button("Удалить автора", __ -> {
            if (grid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери автора", NotificationVariant.LUMO_ERROR);
                return;
            }
            deleteAuthor();
        });
        updateAuthor = new Button("Изменить автора", __ -> {
            if (grid.getSelectedItems().isEmpty()) {
                VaadinUtil.showTopDefaultNotification("Выбери автора", NotificationVariant.LUMO_ERROR);
                return;
            }
            updateDialog().open();
        });

        searchField = new TextField();
        searchField.setPlaceholder("Поиск");
        searchField.addValueChangeListener(event -> {
            String searchTerm = event.getValue().trim();
            dataProvider.setFilter(author -> {
                boolean matchesLastName = author.getAuthorLastName().toLowerCase().contains(searchTerm.toLowerCase());
                boolean matchesFirstName = author.getAuthorName().toLowerCase().contains(searchTerm.toLowerCase());
                boolean matchesPatronymic = author.getAuthorPatronymic().toLowerCase().contains(searchTerm.toLowerCase());
                return matchesLastName || matchesFirstName || matchesPatronymic;
            });
        });
    }

    private Dialog createAddDialog() {
        var lastNameT = new TextField("Фамилия");
        var firstNameT = new TextField("Имя");
        var patronymicT = new TextField("Отчество");

        HorizontalLayout horizontalLayout = new HorizontalLayout(lastNameT, firstNameT, patronymicT);
        var submit = new Button("Подтвердить", __ -> {
            var saveResult = controllerAuthorSaveOrUpdate.saveAuthor(
                    new Author(firstNameT.getValue(), lastNameT.getValue(), patronymicT.getValue())
            );
            if (saveResult.getStatusCode().is2xxSuccessful()) {
                VaadinUtil.showTopDefaultNotification("Ok", NotificationVariant.LUMO_SUCCESS);
                recalcGrid();
            } else {
                VaadinUtil.showTopDefaultNotification("Error", NotificationVariant.LUMO_ERROR);
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout(horizontalLayout, submit);
        verticalLayout.setAlignSelf(Alignment.END, submit);
        Dialog dialog = new Dialog(verticalLayout);

        VaadinUtil.attachCloseButton(dialog);
        return dialog;
    }

    private void deleteAuthor() {
        Author author = grid.getSelectedItems().stream().findFirst().orElseThrow();
        var generalResponseResponseEntity = controllerAuthorDeleted.deleteAuthor(author.getId());
        VaadinUtil.showOkNotOkNotification(generalResponseResponseEntity);
        recalcGrid();
    }

    private Dialog updateDialog() {
        var author = grid.getSelectedItems().stream().findFirst().orElseThrow();
        var lastName = new TextField("Фамилия");
        lastName.setSizeFull();
        var firstName = new TextField("Имя");
        firstName.setSizeFull();
        var patronymic = new TextField("Отчество");
        patronymic.setSizeFull();
        lastName.setValue(author.getAuthorLastName());
        firstName.setValue(author.getAuthorName());
        patronymic.setValue(author.getAuthorPatronymic());
        var updateButton = new Button("Обновить", __ -> {
            author.setAuthorLastName(lastName.getValue());
            author.setAuthorName(firstName.getValue());
            author.setAuthorPatronymic(patronymic.getValue());
            var generalResponseResponseEntity = controllerAuthorSaveOrUpdate.updateAuthor(author);
            VaadinUtil.showOkNotOkNotification(generalResponseResponseEntity);
            recalcGrid();
        });

        VerticalLayout left = new VerticalLayout(lastName, firstName, patronymic);
        HorizontalLayout top = new HorizontalLayout(left);

        VerticalLayout main = new VerticalLayout(top, updateButton);
        main.setAlignSelf(Alignment.END, updateButton);
        Dialog dialog = new Dialog(main);
        dialog.setWidth("30%");
        VaadinUtil.attachCloseButton(dialog);
        return dialog;
    }


    private ComboBox<Author> createFindAuthorBox() {
        var generalResponseResponseEntity = controllerAuthorFind.showAllAuthors();
        List<Author> authors;
        if (!generalResponseResponseEntity.getStatusCode().is2xxSuccessful()) {
            VaadinUtil.showTopDefaultNotification("Ошибка поиска авторов", NotificationVariant.LUMO_ERROR);
            authors = List.of();
        } else {
            authors = Objects.requireNonNull(generalResponseResponseEntity.getBody()).getData();
        }

        var authorBox = new ComboBox<Author>("Авторы");
        authorBox.setItems(authors);
        authorBox.setItemLabelGenerator(author -> "%s %s %s".formatted(
                StringUtils.defaultString(author.getAuthorLastName()),
                StringUtils.defaultString(author.getAuthorName()),
                StringUtils.defaultString(author.getAuthorPatronymic()))
        );

        return authorBox;
    }

    private void recalcGrid() {
        grid.setItems(Objects.requireNonNull(controllerAuthorFind.showAllAuthors().getBody()).getData());
    }

}
