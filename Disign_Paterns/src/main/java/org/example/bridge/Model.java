package org.example.bridge;

/**
 * Интерфейс Model определяет метод drive, который реализуют конкретные модели транспортных средств.
 */
public interface Model {



    /**
     * Выполняет действие в зависимости от переданной строки.
     *
     * @param str Строка, описывающая действие.
     */
    void  drive(String str);
}
