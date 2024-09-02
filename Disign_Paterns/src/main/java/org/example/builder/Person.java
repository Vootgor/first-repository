package org.example.builder;

/**
 * Класс Person представляет собой объект с полями для имени, фамилии, возраста и зарплаты.
 * Он используется в качестве продукта, который будет создан с помощью паттерна Builder.
 */
public class Person {
    String name;
    String surname;
    int age;
    double salary;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
