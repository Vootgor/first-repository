package com.bankApp.bank_account_api.dto;

import com.bankApp.bank_account_api.model.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Объект передачи данных (DTO) для транзакций в банковском приложении. Этот класс используется для
 * передачи информации о транзакции, включая сумму, тип, временную метку и номер счета, к которому
 * относится транзакция.
 */
@Data
public class TransactionDTO {

    /**
     * Сумма транзакции.
     */
    @Schema(description = "Сумма транзакции", example = "150.00")
    private BigDecimal amount;

    /**
     * Тип транзакции (например, депозит, снятие, перевод).
     */
    @Schema(description = "Тип транзакции", example = "DEPOSIT")
    private TransactionType type;

    /**
     * Временная метка, когда была выполнена транзакция.
     */
    @Schema(description = "Временная метка транзакции", example = "2024-10-10T10:15:30")
    private LocalDateTime timestamp;

    /**
     * Номер счета, к которому относится транзакция.
     */
    @Schema(description = "Номер счета, к которому относится транзакция", example = "1234567890")
    private String accountNumber;
}
