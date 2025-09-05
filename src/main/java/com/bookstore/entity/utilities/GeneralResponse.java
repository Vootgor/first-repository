package com.bookstore.entity.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Параметризированный класс для обработки Http статусов и отправки сообщений
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> {

    private String infoText;

    private T data;

    public GeneralResponse(String infoText, T data) {
        this.infoText = infoText;
        this.data = data;
    }

    public GeneralResponse(String infoText) {
        this.infoText = infoText;
    }

    public GeneralResponse(T data) {
        this.data = data;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
