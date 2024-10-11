package com.bankApp.bank_account_api.service.impl;

import com.bankApp.bank_account_api.dto.AccountDTO;
import com.bankApp.bank_account_api.dto.DepositDTO;
import com.bankApp.bank_account_api.dto.TransferDTO;
import com.bankApp.bank_account_api.dto.WithdrawDTO;
import com.bankApp.bank_account_api.model.entity.AccountEntity;
import com.bankApp.bank_account_api.model.entity.TransactionEntity;
import com.bankApp.bank_account_api.model.enums.TransactionType;
import com.bankApp.bank_account_api.repository.AccountRepository;
import com.bankApp.bank_account_api.repository.TransactionRepository;
import com.bankApp.bank_account_api.service.AccountService;
import com.bankApp.bank_account_api.utilities.AccountMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Реализация сервиса для работы с банковскими счетами.
 * <p>
 * Этот класс предоставляет методы для операций с банковскими счетами, включая внесение депозитов,
 * снятие средств и переводы между счетами.
 * </p>
 */
@Service
@Tag(name = "Account Management", description = "Операции с банковскими счетами")
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    /**
     * Конструктор для инициализации зависимостей сервиса.
     *
     * @param accountRepository     Репозиторий для работы с аккаунтами.
     * @param transactionRepository Репозиторий для работы с транзакциями.
     * @param accountMapper         Маппер для преобразования между AccountEntity и AccountDTO.
     */
    public AccountServiceImpl(AccountRepository accountRepository,
        TransactionRepository transactionRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
    }

    /**
     * Внесение депозита на счет.
     *
     * @param depositDTO Данные о депозите, включая номер счета, ПИН-код и сумму.
     * @return Обновленный объект AccountDTO с новой информацией о счете.
     * @throws RuntimeException если аккаунт не найден или неверный ПИН-код.
     */
    @Operation(summary = "Внесение депозита",
        description = "Вносит деньги на указанный аккаунт.")
    @Override
    public AccountDTO deposit(DepositDTO depositDTO) {
        AccountEntity accountEntity = accountRepository.findByAccountNumberAndPin(
                depositDTO.getAccountNumber(), depositDTO.getPin())
            .orElseThrow(() -> new RuntimeException("Аккаунт не найден или неверный ПИН-код"));
        BigDecimal newBalance = accountEntity.getBalance().add(depositDTO.getAmount());
        accountEntity.setBalance(newBalance);
        accountRepository.save(accountEntity);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(depositDTO.getAmount());
        transactionEntity.setType(TransactionType.DEPOSIT);
        transactionEntity.setTimestamp(LocalDateTime.now());
        transactionEntity.setAccount(accountEntity);
        transactionRepository.save(transactionEntity);

        return accountMapper.toDto(accountEntity);
    }

    /**
     * Снятие средств со счета.
     *
     * @param withdrawDTO Данные о снятии, включая номер счета, ПИН-код и сумму.
     * @return Обновленный объект AccountDTO с новой информацией о счете.
     * @throws RuntimeException если аккаунт не найден, неверный ПИН-код или недостаточно средств.
     */
    @Operation(summary = "Снятие средств",
        description = "Снимает деньги с указанного аккаунта.")
    @Override
    public AccountDTO withdraw(WithdrawDTO withdrawDTO) {
        AccountEntity accountEntity = accountRepository.findByAccountNumberAndPin(
                withdrawDTO.getAccountNumber(), withdrawDTO.getPin())
            .orElseThrow(() -> new RuntimeException("Аккаунт не найден или неверный ПИН-код"));
        if (accountEntity.getBalance().compareTo(withdrawDTO.getAmount()) < 0) {
            throw new RuntimeException("Недостаточно средств на счете для снятия указанной суммы");
        }
        BigDecimal newBalance = accountEntity.getBalance().subtract(withdrawDTO.getAmount());
        accountEntity.setBalance(newBalance);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(withdrawDTO.getAmount());
        transactionEntity.setType(TransactionType.WITHDRAWAL);
        transactionEntity.setTimestamp(LocalDateTime.now());
        transactionEntity.setAccount(accountEntity);
        transactionRepository.save(transactionEntity);

        return accountMapper.toDto(accountEntity);
    }

    /**
     * Перевод средств между счетами.
     *
     * @param transferDTO Данные о переводе, включая номер счета, ПИН-код, сумму и номер целевого
     *                    счета.
     * @return Обновленный объект AccountDTO с новой информацией о счете.
     * @throws RuntimeException если аккаунт не найден, неверный ПИН-код, недостаточно средств или
     *                          целевой аккаунт не найден.
     */
    @Operation(summary = "Перевод средств",
        description = "Переводит деньги с одного аккаунта на другой.")
    @Override
    public AccountDTO transfer(TransferDTO transferDTO) {
        AccountEntity accountEntity = accountRepository.findByAccountNumberAndPin(
                transferDTO.getAccountNumber(), transferDTO.getPin())
            .orElseThrow(() -> new RuntimeException("Аккаунт не найден или неверный ПИН-код"));
        if (accountEntity.getBalance().compareTo(transferDTO.getAmount()) < 0) {
            throw new RuntimeException(
                "Недостаточно средств на счете для перевода указанной суммы");
        }
        AccountEntity targerAccountEntity = accountRepository.findById(
                transferDTO.getTargetAccountNumber())
            .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));

        BigDecimal newBalance = accountEntity.getBalance().subtract(transferDTO.getAmount());
        accountEntity.setBalance(newBalance);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transferDTO.getAmount());
        transactionEntity.setType(TransactionType.TRANSFER);
        transactionEntity.setTimestamp(LocalDateTime.now());
        transactionEntity.setAccount(accountEntity);
        transactionRepository.save(transactionEntity);

        BigDecimal newBalanceTarget = accountEntity.getBalance().add(transferDTO.getAmount());
        targerAccountEntity.setBalance(newBalanceTarget);

        TransactionEntity targetTransactionEntity = new TransactionEntity();
        targetTransactionEntity.setAmount(transferDTO.getAmount());
        targetTransactionEntity.setType(TransactionType.TRANSFER);
        targetTransactionEntity.setTimestamp(LocalDateTime.now());
        targetTransactionEntity.setAccount(targerAccountEntity);
        transactionRepository.save(targetTransactionEntity);

        return accountMapper.toDto(accountEntity);
    }
}
