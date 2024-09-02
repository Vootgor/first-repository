package org.example.bridge;

/**
 * Класс Bus расширяет абстрактный класс Vehicle и представляет конкретное транспортное средство - автобус.
 */
public class Bus extends Vehicle{



    /**
     * Конструктор для создания автобуса с указанной моделью.
     *
     * @param model Модель автобуса.
     */
    public Bus(Model model) {
        super(model);
    }



    /**
     * Выполняет действие для автобуса, используя модель.
     */
    @Override
    public void drive() {
        model.drive("Drive bus");
    }
}
