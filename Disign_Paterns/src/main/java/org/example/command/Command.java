package org.example.command;

/**
 * Интерфейс Command определяет метод execute(), который реализуется в конкретных командах.
 */
public interface Command {
    /**
     * Выполняет действие, связанное с командой.
     */
    public void execute();
}
