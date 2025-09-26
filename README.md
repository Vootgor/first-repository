# Bookstore

**Bookstore** — альфа-версия веб-приложения для управления библиотекой.  
Реализованы базовые функции работы с книгами и авторами, с минимальным интерфейсом на Vaadin.

## Основные возможности

- Добавление и редактирование книг
- Добавление и редактирование авторов
- Загрузка и скачивание файлов книг
- Удаление книг и авторов
- Поиск по авторам и книгам
- Базовый фронт на Vaadin (кнопки и простые формы)

---

## Технологии

- Java 17
- Spring Boot 3 (Web, Data JPA)
- Vaadin 24 (UI)
- H2 Database (in-memory)
- Springdoc OpenAPI (Swagger UI)
- TestNG

---

## Структура проекта

```
src/
├─ main/
│ ├─ java/com/bookstore/
│ │ ├─ controller/ # REST контроллеры
│ │ ├─ dao/ # Репозитории
│ │ ├─ dto/ # DTO
│ │ ├─ entity/ # Сущности, enum'ы и utilities классы
│ │ ├─ front/ # Vaadin views
│ │ └─ service/ # Бизнес-логика
│ └─ resources/
│ └─ application.properties
└─ test/ # Unit-тесты
```

---

## Сборка и запуск

### Локально

```bash
./gradlew build
./gradlew bootRun
```

---

## Тесты

- Unit-тесты: `src/test/java/com/bookstore/entity/utilities`

***Запуск***

```bash
./gradlew test
```