package org.example.bridge;

/**
 * Класс Car расширяет абстрактный класс Vehicle и представляет конкретное транспортное средство - автомобиль.
 */
public class Car extends Vehicle{



    /**
     * Конструктор для создания автомобиля с указанной моделью.
     *
     * @param model Модель автомобиля.
     */
    public Car(Model model) {
        super(model);
    }



    /**
     * Выполняет действие для автомобиля, используя модель.
     */
    @Override
    public void drive() {
        model.drive("Drive car");
    }
}
