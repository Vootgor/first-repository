package org.example.builder;


/**
 * Класс PersonBuilderImpl реализует интерфейс PersonBuilder и предоставляет конкретную реализацию
 * для построения объекта Person. Методы позволяют установить значения полей и вернуть готовый объект Person.
 */
public class PersonBuilderImpl implements PersonBuilder{

    Person person = new Person();



    /**
     * Устанавливает имя для объекта Person.
     *
     * @param name Имя человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    @Override
    public PersonBuilder setName(String name) {
        person.name = name;
        return this;
    }



    /**
     * Устанавливает фамилию для объекта Person.
     *
     * @param surname Фамилия человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    @Override
    public PersonBuilder setSurname(String surname) {
        person.surname = surname;
        return this;
    }



    /**
     * Устанавливает возраст для объекта Person.
     *
     * @param age Возраст человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    @Override
    public PersonBuilder setAge(int age) {
        person.age = age;
        return this;
    }



    /**
     * Устанавливает зарплату для объекта Person.
     *
     * @param salary Зарплата человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    @Override
    public PersonBuilder setSalary(int salary) {
        person.salary = salary;
        return this;
    }



    /**
     * Создает и возвращает объект Person с установленными параметрами.
     *
     * @return Экземпляр класса Person.
     */
    @Override
    public Person build() {
        return person;
    }
}
