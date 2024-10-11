package com.bankApp.bank_account_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для представления информации о пользователе в банковском приложении.
 * Этот класс используется для передачи данных о пользователе, включая имя, PIN-код и список
 * связанных счетов.
 */
@Data
public class UserDTO {

    /**
     * Имя пользователя.
     */
    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private final String name;

    /**
     * PIN-код пользователя, используемый для аутентификации.
     */
    @Schema(description = "PIN-код пользователя", example = "1234")
    private final String pin;

    /**
     * Список счетов, принадлежащих пользователю.
     */
    @Schema(description = "Список счетов, принадлежащих пользователю")
    private final List<AccountDTO> accountDTO;
}
