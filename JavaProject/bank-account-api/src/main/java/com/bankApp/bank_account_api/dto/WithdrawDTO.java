package com.bankApp.bank_account_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для представления информации о запросе на снятие средств из
 * банковского счета. Этот класс используется для передачи данных о снятии средств, включая номер
 * счета, PIN-код и сумму.
 */
@Data
public class WithdrawDTO {

    /**
     * Номер банковского счета, с которого необходимо снять средства.
     */
    @Schema(description = "Номер банковского счета", example = "1234567890")
    private final String accountNumber;

    /**
     * PIN-код пользователя, используемый для аутентификации при снятии средств.
     */
    @Schema(description = "PIN-код пользователя", example = "1234")
    private final String pin;

    /**
     * Сумма, которую необходимо снять с банковского счета.
     */
    @Schema(description = "Сумма для снятия", example = "100.00")
    private final BigDecimal amount;
}
