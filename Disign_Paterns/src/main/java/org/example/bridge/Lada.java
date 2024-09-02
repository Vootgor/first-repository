package org.example.bridge;

/**
 * Класс Lada реализует интерфейс Model и представляет конкретную модель транспортного средства - Lada.
 */
public class Lada implements Model{



    /**
     * Выполняет действие, связанное с моделью Lada, и выводит сообщение в консоль.
     *
     * @param str Строка, описывающая действие.
     */
    @Override
    public void drive(String str) {
        System.out.println(str + " lada");
    }
}
