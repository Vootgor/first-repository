package org.example.bridge;

/**
 * Абстрактный класс Vehicle представляет транспортное средство.
 * Он содержит ссылку на объект Model, который используется для выполнения действий.
 */
public abstract class Vehicle {
    Model model;



    /**
     * Конструктор для создания транспортного средства с указанной моделью.
     *
     * @param model Модель транспортного средства.
     */
    public Vehicle(Model model) {
        this.model = model;
    }



    /**
     * Абстрактный метод для выполнения действия, зависящего от конкретного транспортного средства.
     */
    public abstract void drive();
}
