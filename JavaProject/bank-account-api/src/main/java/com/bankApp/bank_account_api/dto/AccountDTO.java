package com.bankApp.bank_account_api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для информации о счете.
 * Этот класс используется для передачи данных о счете клиента,
 * включая баланс и имя пользователя.
 */
@Data
public class AccountDTO {

    /**
     * Баланс счета.
     */
    @Schema(description = "Баланс счета", example = "1500.00")
    private final BigDecimal balance;

    /**
     * Имя пользователя, которому принадлежит счет.
     */
    @Schema(description = "Имя пользователя, которому принадлежит счет", example = "John Doe")
    private final String userName;
}
