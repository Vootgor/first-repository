package com.example.main.entity.utilities;
/** Утилитарный класс для получения аббревиатуры типа файла*/
public final class GettingFileType {
    /**
     * Метод принимает слово с конца ищет первое вхождение знака точка, затем обрезает
     * все символы до точки (включительно) и возвращает обрезанную строку.
     * @param word слово в котом нужно найти точку и обрезать
     * @return возвращает новый String с полученым результатом после всех манипуляций
     */
    public static String gettingFileType(String word){
        String fileType;
        int position = word.lastIndexOf('.');
        fileType = word.substring(position + 1);
        return fileType;
    }


}
