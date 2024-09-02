package org.example.builder;

/**
 * Интерфейс PersonBuilder определяет методы для построения объекта Person.
 * Каждый метод возвращает экземпляр PersonBuilder
 */
public interface PersonBuilder {



    /**
     * Устанавливает имя для человека.
     *
     * @param name Имя человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    PersonBuilder setName(String name);



    /**
     * Устанавливает фамилию для человека.
     *
     * @param surname Фамилия человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    PersonBuilder setSurname(String surname);



    /**
     * Устанавливает возраст для человека.
     *
     * @param age Возраст человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    PersonBuilder setAge(int age);



    /**
     * Устанавливает зарплату для человека.
     *
     * @param salary Зарплата человека.
     * @return Текущий экземпляр PersonBuilder.
     */
    PersonBuilder setSalary(int salary);



    /**
     * Создает объект Person с установленными параметрами.
     *
     * @return Экземпляр класса Person.
     */
    Person build();
}
