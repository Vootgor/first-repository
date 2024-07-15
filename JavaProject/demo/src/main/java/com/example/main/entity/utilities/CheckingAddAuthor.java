package com.example.main.entity.utilities;
import com.example.main.entity.Author;

/** Утилитарный класс для проверки правильности написания полей
 * перед сохранением экземпляра класса Author в базу данных*/
public final class CheckingAddAuthor {



    /**
     * Метод принимает 3 строки - имя, фамилию и отчество. Проверяет чтоб значения были не пустые и не null.
     * Имя и фамилия бросают exception, отчество допускает null(если отчество пустое то присваивается null).
     * С помощью этих значений создаётся и возвращается экземпляр класса Author.
     * @param author
     * authorName - Принимает имя автора, если значение null или пустое бросает exception,
     * иначе удаляем пробелы и поднимаем первую букву. Еще раз проверяем пустое ли
     * значение или состоит только из символов (путём удаления пробелов и символов,
     * если это так бросаем exception.
     * authorLastName - Принимает фамилию автора, если значение null или пустое бросает exception,
     * иначе удаляем пробелы и поднимаем первую букву. Еще раз проверяем пустое ли значение или
     * состоит только из символов (путём удаления пробелов и символов), если это так бросаем exception.
     * authorPatronymic - Принимает отчество автора, проверяем равно ли значение null.
     * Если не равно, то удаляет пробелы и поднимает первую букву. Еще раз проверяем пустое ли значение
     * или состоит только из символов (путём удаления пробелов и символов), если это так бросаем exception.
     *
     * @return возвращает экземпляр класса Author созданный с помощью переданных строк которые были
     * проверенны и отформатированны.
     */


    //todo на этот метод можно написать unit тест в качестве практики
    //todo поправить описание метода
    public static Author checkingTransmittedArgumentsForAuthor(Author author) {

        String authorName = author.getAuthorName();
        String authorLastName = author.getAuthorLastName();
        String authorPatronymic = author.getAuthorPatronymic();

        //проверка имени
        System.out.println("-------------Класс CheckingAddAuthor начал работу----------------- ");
        if (NullAndEmpty.stringIsNullOrEmpty(authorName)) {
            throw new IllegalArgumentException("Братюнь имя автора не может быть пустым или null. Сделай нормально");
        } else {
            authorName = authorName.trim();
            authorName = ElevateFirstLetter.raiseFirstLetter(authorName);
            if (SymbolsAndWhitespace.removalSymbolsAndWhitespace(authorName).isEmpty()) {
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
            if (SymbolsAndWhitespace.removalSymbolsAndWhitespace(authorLastName).isEmpty()) {
                throw new IllegalArgumentException("Братюнь фамилия автора не может быть пустой или состоять " +
                        "только из символов и пробелов. Сделай нормально");
            }
        }

        //проверка отчества
        if (authorPatronymic != null) {
            authorPatronymic = authorPatronymic.trim();
            authorPatronymic = ElevateFirstLetter.raiseFirstLetter(authorPatronymic);
            if (SymbolsAndWhitespace.removalSymbolsAndWhitespace(authorPatronymic).isEmpty()) {
                throw new IllegalArgumentException("Братюнь отчество автора не может состоять " +
                        "только из символов и пробелов. Сделай нормально");
            }
        }

        author.setAuthorName(authorName);
        author.setAuthorLastName(authorLastName);
        author.setAuthorPatronymic(authorPatronymic);

        System.out.println("Передаём из класса CheckingAddAuthor" + author);

        System.out.println("-------------Класс CheckingAddAuthor завершил работу----------------- ");
        return author;
    }
}
