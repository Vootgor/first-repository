package com.example.main.entity.utilities;

import com.example.main.entity.Author;

public final class CheckingAddAuthor {


    /**
     * Метод принимает 3 строки - имя, фамилию и отчество. Проверяет чтоб значения были не пустые и не null.
     * Uмя и фамилия бросают exception, отчество допускает null(если отчество пустое то присваивается null).
     * С помощью этих значений создаётся и возвращается экземпляр класса Author.
     *
     * @param authorName       принимает имя автора, если значение null или пустое бросает exception
     *                         , иначе удаляем пробелы и поднимаем первую букву. Еще раз проверяем пустое
     *                         ли значение, если пустое то бросаем exception.
     * @param authorLastName   принимает фамилию автора, если значение null или пустое бросает exception
     *      *                  , иначе удаляем пробелы и поднимаем первую букву. Еще раз проверяем пустое
     *      *                         ли значение, если пустое то бросаем exception.
     * @param authorPatronymic принимает отчество автора, в которой проверяется равно ли значение null.
     *                         Если не равно, то удаляет пробелы и поднимает первую букву,
     *                         затем еще раз проверяет пуста ли строка и присваиваем null если это так
     *                         , иначе передаём отформатированную строку.
     * @return возвращает экземпляр класса Author созданный с помощью переданых строк которые были
     * проверенны и отформатированны.
     */
    //todo сделать проверку на строку только из символов и пробелов, выбрасывать exception
    public static Author checkingTransmittedArgumentsForAuthor(String authorName
            , String authorLastName, String authorPatronymic) {

        //проверка имени
        System.out.println("-------------Класс CheckingAddAuthor начал работу----------------- ");
        if (NullAndEmpty.stringIsNullOrEmpty(authorName)) {
            throw new IllegalArgumentException("Братюнь имя автора не может быть пустым или null. Сделай нормально");
        } else {
            authorName = authorName.trim();
            authorName = ElevateFirstLetter.raiseFirstLetter(authorName);
            if (authorName.isEmpty()) {
                throw new IllegalArgumentException("Братюнь имя автора не может быть пустым или состоять " +
                        "только из символов и пробелов. Сделай нормально");
            }
        }

        // проверка фамилии
        if (NullAndEmpty.stringIsNullOrEmpty(authorLastName)) {
            throw new IllegalArgumentException("Братюнь фамилия автора не может быть пустой или null. Сделай нормально");
        } else {
            authorLastName = authorLastName.trim();
            authorLastName = ElevateFirstLetter.raiseFirstLetter(authorLastName);
            if (authorLastName.isEmpty()) {
                throw new IllegalArgumentException("Братюнь фамилия автора не может быть пустой или состоять " +
                        "только из символов и пробелов. Сделай нормально");
            }
        }

        //проверка отчества
        if (authorPatronymic != null) {
            System.out.println("Удаляем пробелы из отчества метод CheckingAddAuthor");
            authorPatronymic = authorPatronymic.trim();
            System.out.println("Поднимаем первую букву отчества метод CheckingAddAuthor");
            authorPatronymic = ElevateFirstLetter.raiseFirstLetter(authorPatronymic);
            if (!authorPatronymic.isEmpty()) {
                authorPatronymic = null;
            }
        }

        Author trueAuthor = new Author(authorName, authorLastName, authorPatronymic);

        System.out.println("Передаём из класса CheckingAddAuthor" + trueAuthor);

        System.out.println("-------------Класс CheckingAddAuthor завершил работу----------------- ");
        return trueAuthor;
    }
}
