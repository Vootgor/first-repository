package com.example.main.entity.utilities;

public final class ElevateFirstLetter {

    //проверяет если строка не пустая то поднимает первую букву
    public static String raiseFirstLetter(String word) {
        if (word.isEmpty()) {
            return word;
        }
        word = word.toLowerCase();
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}
