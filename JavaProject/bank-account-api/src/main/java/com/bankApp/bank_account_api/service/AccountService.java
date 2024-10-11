package com.bankApp.bank_account_api.service;

import com.bankApp.bank_account_api.dto.AccountDTO;
import com.bankApp.bank_account_api.dto.DepositDTO;
import com.bankApp.bank_account_api.dto.TransferDTO;
import com.bankApp.bank_account_api.dto.WithdrawDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Интерфейс сервиса для работы с банковскими счетами.
 * <p>
 * Данный интерфейс определяет методы для выполнения операций с аккаунтами, включая внесение, снятие
 * и перевод средств.
 * </p>
 */
@Tag(name = "Account Management", description = "Операции с банковскими счетами")
public interface AccountService {

    /**
     * Вносит деньги на аккаунт.
     *
     * @param depositDTO данные для внесения средств
     * @return обновленный аккаунт в виде DTO
     * @throws RuntimeException если аккаунт не найден или неверный ПИН-код
     */
    @Operation(summary = "Внести деньги на аккаунт",
        description = "Вносит деньги на аккаунт. " +
            "Возвращает обновленный аккаунт в виде DTO.")
    AccountDTO deposit(DepositDTO depositDTO);

    /**
     * Снимает деньги с аккаунта.
     *
     * @param withdrawDTO данные для снятия средств
     * @return обновленный аккаунт в виде DTO
     * @throws RuntimeException если аккаунт не найден, неверный ПИН-код или недостаточно средств
     */
    @Operation(summary = "Снять деньги с аккаунта",
        description = "Снимает деньги с аккаунта. " +
            "Возвращает обновленный аккаунт в виде DTO.")
    AccountDTO withdraw(WithdrawDTO withdrawDTO);

    /**
     * Переводит деньги с одного аккаунта на другой.
     *
     * @param transferDTO данные для перевода средств
     * @return обновленный аккаунт отправителя в виде DTO
     * @throws RuntimeException если аккаунт отправителя не найден, неверный ПИН-код, недостаточно
     *                          средств или аккаунт получателя не найден
     */
    @Operation(summary = "Перевести деньги с одного аккаунта на другой",
        description = "Переводит деньги с одного аккаунта на другой. " +
            "Возвращает обновленный аккаунт отправителя в виде DTO.")
    AccountDTO transfer(TransferDTO transferDTO);
}
