package org.example.command;

/**
 * Класс TurnOnLight реализует интерфейс Command и представляет команду для включения света.
 */
public class TurnOnLight implements Command{

    private Light light;



    /**
     * Конструктор для создания команды включения света.
     *
     * @param light Экземпляр класса Light, связанный с данной командой.
     */
    public TurnOnLight(Light light) {
        this.light = light;
    }



    /**
     * Выполняет команду включения света, вызывая метод turnOn() у объекта Light.
     */
    @Override
    public void execute() {
        light.turnOn();
    }
}
