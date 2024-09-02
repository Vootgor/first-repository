package org.example.command;

/**
 * Класс RemoteControl выступает инициатором команд.
 * Он позволяет устанавливать команды и выполнять их по нажатию кнопки.
 */
public class RemoteControl {
    private Command command;

    /**
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    /**
     * Выполняет установленную команду, вызывая её метод execute().
     */
    public void pressButton() {
        command.execute();
    }
}
