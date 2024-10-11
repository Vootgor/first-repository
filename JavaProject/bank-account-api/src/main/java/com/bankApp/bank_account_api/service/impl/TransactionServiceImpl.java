package com.bankApp.bank_account_api.service.impl;

import com.bankApp.bank_account_api.dto.TransactionDTO;
import com.bankApp.bank_account_api.model.entity.AccountEntity;
import com.bankApp.bank_account_api.model.entity.TransactionEntity;
import com.bankApp.bank_account_api.repository.AccountRepository;
import com.bankApp.bank_account_api.repository.TransactionRepository;
import com.bankApp.bank_account_api.service.TransactionService;
import com.bankApp.bank_account_api.utilities.TransactionMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с транзакциями.
 * <p>
 * Данный класс отвечает за получение списка транзакций для заданного аккаунта. Он использует
 * репозитории для доступа к данным и мапперы для преобразования сущностей в DTO.
 * </p>
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    /**
     * Конструктор для создания экземпляра TransactionServiceImpl.
     *
     * @param accountRepository     репозиторий для работы с аккаунтами
     * @param transactionRepository репозиторий для работы с транзакциями
     * @param transactionMapper     маппер для преобразования транзакций в DTO
     */
    public TransactionServiceImpl(AccountRepository accountRepository,
        TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Получает список всех транзакций для заданного аккаунта.
     *
     * @param accountId идентификатор аккаунта
     * @return список транзакций в виде DTO
     * @throws RuntimeException если аккаунт не найден
     */
    @Override
    public List<TransactionDTO> getByAllTransaction(String accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
            .orElseThrow(
                () -> new RuntimeException("Аккаунт не найден или введён не верный accountId"));
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByAccount(
            accountEntity);
        return transactionEntities.stream()
            .map(transactionMapper::toDto)
            .collect(Collectors.toList());
    }
}
