# Bank Account API

Это RESTful API для управления банковскими счетами и транзакциями. Пользователи могут создавать счета, вносить и снимать средства, а также просматривать историю транзакций.

## Технологии

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- MapStruct
- Liquibase для управления миграциями
- Swagger для документирования API

## Структура проекта

- `model`: Содержит сущности, представляющие данные приложения.
    - `entity`: Определяет сущности, такие как `UserEntity`, `AccountEntity` и `TransactionEntity`.
    - `enums`: Содержит перечисления, такие как `TransactionType`.

- `repository`: Интерфейсы для доступа к данным, использующие Spring Data JPA.

- `service`: Определяет бизнес-логику приложения.
    - `impl`: Реализации сервисов.

- `utilities`: Утилиты для маппинга DTO и обработки общих ответов.

## API эндпоинты

### Пользователь

- **Создать пользователя**
    - `POST /api/users`
    - Пример тела запроса:
      ```json
      {
        "name": "Имя пользователя",
        "pin": "1234"
      }
      ```

- **Получить пользователя**
    - `GET /api/users/{id}`

### Счет

- **Внести средства на счет**
    - `POST /api/accounts/deposit`
    - Пример тела запроса:
      ```json
      {
        "accountNumber": "номер_счета",
        "pin": "1234",
        "amount": 100.00
      }
      ```

- **Снять средства со счета**
    - `POST /api/accounts/withdraw`
    - Пример тела запроса:
      ```json
      {
        "accountNumber": "номер_счета",
        "pin": "1234",
        "amount": 50.00
      }
      ```

- **Перевести средства между счетами**
    - `POST /api/accounts/transfer`
    - Пример тела запроса:
      ```json
      {
        "accountNumber": "номер_счета",
        "pin": "1234",
        "targetAccountNumber": "номер_целевого_счета",
        "amount": 30.00
      }
      ```

### Транзакции

- **Получить все транзакции по счету**
    - `GET /api/accounts/{accountId}/transactions`