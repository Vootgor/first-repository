package com.example.main.front;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("library")
@RouteAlias("/")
public class MainView extends VerticalLayout {


    public MainView() {
        Button button = new Button(TextConstants.ADD_NEW_BOOK, __ -> UI.getCurrent().navigate(AddBookVIew.class));
        Button button1 = new Button(TextConstants.AUTHORS, __ -> UI.getCurrent().navigate(AuthorView.class));
        Button button2 = new Button(TextConstants.BOOKS, __ -> UI.getCurrent().navigate(BookView.class));

        add(button, button1, button2);
        setAlignItems(Alignment.CENTER);
    }


}
