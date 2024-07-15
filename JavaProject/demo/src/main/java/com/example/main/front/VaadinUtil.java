package com.example.main.front;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class VaadinUtil {

    public static Button getMenuButton() {
        return new Button("В меню", __ -> UI.getCurrent().navigate(MainView.class));
    }

    public static void showTopDefaultNotification(String text, NotificationVariant type) {
        Notification notification = new Notification(text, 3000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(type);
        notification.open();
    }

    public static void showOkNotOkNotification(ResponseEntity<?> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            showTopDefaultNotification("Ok", NotificationVariant.LUMO_SUCCESS);
        } else {
            showTopDefaultNotification("NotOk", NotificationVariant.LUMO_ERROR);
        }

    }

    public static Dialog yesNoDialog(String dialogLabel, ComponentEventListener<ClickEvent<Button>> confirmEvent) {
        Dialog dialog = new Dialog(dialogLabel);
        var confirmButton = new Button("Создать");
        confirmButton.addClickListener(__ -> {
            confirmEvent.onComponentEvent(__);
            dialog.close();
        });
        confirmButton.setClassName("blue-button");
        var cancelButton = new Button("Закрыть",
                __ -> dialog.close());
        cancelButton.setClassName("red-transparent-button");


        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.add(confirmButton, cancelButton);
        dialog.add(horizontalLayout);
        return dialog;
    }

    public static void attachCloseButton(Dialog dialog) {
        Button closeButton = new Button(new Icon("lumo", "cross"), __ -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);
    }

}