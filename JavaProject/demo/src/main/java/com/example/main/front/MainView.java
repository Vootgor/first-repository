package com.example.main.front;

import com.example.main.controller.only_for_books.ControllerBookFind;
import com.example.main.entity.Book;
import com.example.main.entity.enam.EvaluationOfBook;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route("/testtt")
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
        ComboBox<Integer> evaluationOfBookComboBox = new ComboBox<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        evaluationOfBookComboBox.setItems(arrayList);
/*        evaluationOfBookComboBox.setItemLabelGenerator(selectedEvaluation -> {
            if (EvaluationOfBook.EIGHT == selectedEvaluation) {
                return "8 восемь!";
            }
            return "poshel na hui";
        });*/
      //  EvaluationOfBook value = evaluationOfBookComboBox.getValue();
        add(new H1("Список книг"), evaluationOfBookComboBox, showAllButton, grid);
    }


}
