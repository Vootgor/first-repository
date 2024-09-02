package org.example.bridge;

/**
 * Класс Audi реализует интерфейс Model и представляет конкретную модель транспортного средства - Audi.
 */
public class Audi implements Model{



    /**
     * Выполняет действие, связанное с моделью Audi, и выводит сообщение в консоль.
     *
     * @param str Строка, описывающая действие.
     */
    @Override
    public void drive(String str) {
        System.out.println(str + " audi");
    }
}
