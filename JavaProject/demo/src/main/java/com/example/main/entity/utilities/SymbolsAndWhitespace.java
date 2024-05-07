package com.example.main.entity.utilities;
    /** Утилитарный класс для работы с символами и пробелами.*/
public final class SymbolsAndWhitespace {

    /**
     * Удаляет все символы и пробелы вначале и в конце строки.
     * @param word строка, из которой нужно удалить символы и пробелы.
     * @word строка без символов и пробелов в начале и в конце.*/
    public static String removalSymbolsAndWhitespace(String word){
        return word.replaceAll("^[\\p{Punct}\\s]+", "") //удаляет все знаки пунктуации и пробелы в начале строки
                .replaceAll("[\\p{Punct}\\s]+$", "") //удаляет все знаки пунктуации и пробелы в конце строки
                .replaceAll("[\\p{Punct}\\s]+", " ") //удаляет символы между словами
                .replaceAll("\\s{2,}", " "); // оставляет только один пробел между словами
    }

}
