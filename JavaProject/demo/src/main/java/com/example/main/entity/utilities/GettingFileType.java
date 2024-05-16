package com.example.main.entity.utilities;

public final class GettingFileType {
    /**
     * Метод принимает слово с конца ищет первое вхождение знака точка, затем обрезает
     * все символы до точки (включительно) и возвращает обрезанную строку.
     * @param word слово в котом нужно найти точку и обрезать
     * @return обрезанное слово
     */
    public static String gettingFileType(String word){
        System.out.println("Получили " + word);
        String fileType;
        int position = word.lastIndexOf('.');
        System.out.println("Нашли точку на позиции " + position);
        fileType = word.substring(position + 1);
        System.out.println("После обрезания получили " + fileType);
        return fileType;
    }


}
