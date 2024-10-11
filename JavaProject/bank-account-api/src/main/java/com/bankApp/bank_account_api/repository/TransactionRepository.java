package com.bankApp.bank_account_api.repository;

import com.bankApp.bank_account_api.model.entity.AccountEntity;
import com.bankApp.bank_account_api.model.entity.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями {@link TransactionEntity}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD и получения информации
 * о транзакциях, связанных с банковскими счетами.
 * </p>
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findAllByAccount(AccountEntity account);
}
