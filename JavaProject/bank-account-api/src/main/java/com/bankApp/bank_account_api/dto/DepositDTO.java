package com.bankApp.bank_account_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для внесения депозита на счет. Этот класс используется для передачи
 * информации о вносимой сумме, номере счета и ПИН-коде пользователя, связанном с этим счетом.
 */
@Data
public class DepositDTO {

    /**
     * Номер счета, на который будет внесен депозит.
     */
    @Schema(description = "Номер счета, на который будет внесен депозит", example = "1234567890")
    private final String accountNumber;

    /**
     * ПИН-код пользователя для аутентификации операции.
     */
    @Schema(description = "ПИН-код пользователя для аутентификации операции", example = "1234")
    private final String pin;

    /**
     * Сумма, которую необходимо внести на счет.
     */
    @Schema(description = "Сумма, которую необходимо внести на счет", example = "100.00")
    private final BigDecimal amount;
}
