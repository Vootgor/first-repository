package org.example.command;
/**
 * Класс TurnOffLight реализует интерфейс Command и представляет команду для выключения света.
 */
public class TurnOffLight implements Command{
    Light light;


    /**
     * Конструктор для создания команды выключения света.
     *
     * @param light Экземпляр класса Light, связанный с данной командой.
     */
    public TurnOffLight(Light light) {
        this.light = light;
    }



    /**
     * Выполняет команду выключения света, вызывая метод turnOff() у объекта Light.
     */
    @Override
    public void execute() {
    light.turnOff();
    }
}
