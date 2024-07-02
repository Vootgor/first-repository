package com.example.main.entity.enums;

public enum Genre {
    FANTASY("Фентези"),
    PSYCHOLOGY("Психология"),
    HISTORY("История"),
    PHILOSOPHY("Философия"),
    DETECTIVE("Детектив"),
    HORROR("Хоррор"),
    MOTIVATIONAL_LITERATURE("Мотивационная литература"),
    FINANCE("Финансы"),
    UNKNOWN("-");

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
