package org.example;

import org.example.bridge.*;
import org.example.builder.Person;
import org.example.builder.PersonBuilderImpl;
import org.example.command.*;

public class Main {
    public static void main(String[] args) {
        Person person = new PersonBuilderImpl().setName("Ivan").setSurname("Ivanov").setAge(64).setSalary(4891).build();
        System.out.println(person);



        Vehicle car = new Car(new Lada());
        Vehicle bus = new Bus(new Audi());
        Vehicle truck = new Truck(new Audi());
        car.drive();
        bus.drive();
        truck.drive();



        Light light = new Light();
        Command turnOn = new TurnOnLight(light);
        Command turnOff = new TurnOffLight(light);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOn);
        remote.pressButton();
        remote.setCommand(turnOff);
        remote.pressButton();

    }
}