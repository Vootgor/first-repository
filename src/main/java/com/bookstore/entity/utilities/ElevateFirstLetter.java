package com.bookstore.entity.utilities;

/** Утилитарный класс для преобразования первой буквы в переданной строке в верхний регистр.*/
public final class ElevateFirstLetter {

    /**
     * проверяет если строка не пустая, преобразует все символы в нижний регистр затем поднимает первую букву
     * @param word строка в которой нужно поднять первую букву
     * @return возвращат строку с поднятой первой буквой
     */

    public static String raiseFirstLetter(String word) {
        if (word.isEmpty()) {
            return word;
        }
        word = word.toLowerCase();
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}
