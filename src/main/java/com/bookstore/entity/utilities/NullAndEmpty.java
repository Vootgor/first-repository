package com.bookstore.entity.utilities;

/** Утилитарный класс для пустых и нулевых значений.*/
public final class NullAndEmpty {
    /**
     * Проверяет что строка null или пустая.
     * @param string строка, в которой проверяется значение.
     * @return  возвращает true если строка null или пустая.
     * */
    public static boolean stringIsNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
