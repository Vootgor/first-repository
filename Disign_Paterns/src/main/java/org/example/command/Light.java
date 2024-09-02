package org.example.command;

/**
 * Класс Light является получателем команд.
 * Содержит методы для включения и выключения света.
 */
public class Light {

    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
