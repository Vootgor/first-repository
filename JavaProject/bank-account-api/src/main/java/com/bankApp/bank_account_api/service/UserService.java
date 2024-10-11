package com.bankApp.bank_account_api.service;

import com.bankApp.bank_account_api.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Интерфейс сервиса для управления пользователями банковского приложения.
 * <p>
 * Данный интерфейс определяет методы для создания пользователей и получения информации о них.
 * </p>
 */
@Tag(name = "User Management", description = "Операции с пользователями банковского приложения")
public interface UserService {

    /**
     * Создает нового пользователя в системе.
     *
     * @param userDTO объект, содержащий данные пользователя, которые необходимо сохранить
     * @return объект UserDTO, представляющий сохраненного пользователя
     */
    @Operation(summary = "Создать нового пользователя",
        description = "Создает нового пользователя в системе.")
    UserDTO createUser(UserDTO userDTO);

    /**
     * Получает информацию о пользователе по его идентификатору.
     *
     * @param id идентификатор пользователя, информацию о котором требуется получить
     * @return объект UserDTO, представляющий найденного пользователя
     * @throws RuntimeException если пользователь с указанным идентификатором не найден
     */
    @Operation(summary = "Получить информацию о пользователе",
        description = "Получает информацию о пользователе по его идентификатору.")
    UserDTO getUser(String id);
}
