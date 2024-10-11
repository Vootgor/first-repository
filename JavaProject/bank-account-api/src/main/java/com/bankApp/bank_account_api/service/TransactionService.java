package com.bankApp.bank_account_api.service;

import com.bankApp.bank_account_api.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

/**
 * Интерфейс сервиса для работы с транзакциями банковских счетов.
 * <p>
 * Данный интерфейс определяет методы для получения информации о транзакциях, связанных с
 * банковскими аккаунтами.
 * </p>
 */
@Tag(name = "Transaction Management", description = "Операции с транзакциями банковских счетов")
public interface TransactionService {

    /**
     * Получает список всех транзакций для указанного аккаунта.
     *
     * @param accountId идентификатор аккаунта, для которого требуется получить транзакции
     * @return список транзакций в виде DTO
     * @throws RuntimeException если аккаунт не найден
     */
    @Operation(summary = "Получить все транзакции по аккаунту",
        description = "Получает список всех транзакций для указанного аккаунта.")
    List<TransactionDTO> getByAllTransaction(String accountId);

}
