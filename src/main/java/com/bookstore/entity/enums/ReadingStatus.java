package com.bookstore.entity.enums;

public enum ReadingStatus {
    UNKNOWN("-"),
    READING_NOW("Читаю"),
    WAS_READ("Прочтено"),
    GOING_TO_READ("Собираюсь читать");

    private final String status;

    ReadingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
