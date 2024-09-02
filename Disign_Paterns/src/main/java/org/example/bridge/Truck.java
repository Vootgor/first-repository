package org.example.bridge;

/**
 * Класс Truck расширяет абстрактный класс Vehicle и представляет конкретное транспортное средство - грузовик.
 */
public class Truck extends Vehicle{



    /**
     * Конструктор для создания автомобиля с указанной моделью.
     *
     * @param model Модель грузовика.
     */
    public Truck(Model model) {
        super(model);
    }



    /**
     * Выполняет действие для грузовика, используя модель.
     */
    @Override
    public void drive() {
        model.drive("Drive truck");
    }
}
