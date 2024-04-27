package com.example.main.front;

import com.example.main.controller.ControllerBookFind;
import com.example.main.entity.Book;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("api/books")
public class MainView extends VerticalLayout {

    private final ControllerBookFind restControllerBook;
    private final Grid<Book> grid = new Grid<>(Book.class);

    @Autowired
    public MainView(ControllerBookFind restControllerBook) {
        this.restControllerBook = restControllerBook;

        Button showAllButton = new Button("Показать все книги");
        showAllButton.addClickListener(event -> {
            grid.setItems(restControllerBook.showAllBooks());
        });

        add(new H1("Список книг"), showAllButton, grid);
    }
}

//     //метод для теста
//    MainView() {
//        add(new H1("Путин охуенный чувак"));
//        // Создаем кнопку "Добавить книгу"
//        Button addButton = new Button("Добавить книгу");
//
//        // Добавляем стили для кнопки
//        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//
//        // Добавляем обработчик нажатия кнопки
//        addButton.addClickListener(event -> {
//            restControllerBook.showAllBooks();
//            // Здесь вы можете добавить логику для обработки нажатия кнопки
//            // Например, открыть форму для добавления книги
//        });
//
//        // Добавляем кнопку на интерфейс
//        add(addButton);
//    }
