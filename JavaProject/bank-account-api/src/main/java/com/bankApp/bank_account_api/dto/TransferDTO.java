package com.bankApp.bank_account_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для операций перевода средств в банковском приложении. Этот класс
 * используется для передачи информации о переводе, включая номер счета отправителя, PIN-код, сумму
 * и номер целевого счета.
 */
@Data
public class TransferDTO {

    /**
     * Номер счета отправителя.
     */
    @Schema(description = "Номер счета отправителя", example = "1234567890")
    private final String accountNumber;

    /**
     * PIN-код отправителя, необходимый для подтверждения операции.
     */
    @Schema(description = "PIN-код отправителя", example = "1234")
    private final String pin;

    /**
     * Сумма, которую необходимо перевести.
     */
    @Schema(description = "Сумма, которую необходимо перевести", example = "150.00")
    private final BigDecimal amount;

    /**
     * Номер целевого счета, на который будет произведен перевод.
     */
    @Schema(description = "Номер целевого счета, на который будет произведен перевод", example = "0987654321")
    private final String targetAccountNumber;
}
