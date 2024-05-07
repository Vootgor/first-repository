package com.example.main.entity.utilities;

public final class NullAndEmpty {

    public static boolean stringIsNullOrEmpty(String string) {
        System.out.println("Отработал метод stringIsNullOrEmpty");
        return string == null || string.isEmpty();
    }
}
