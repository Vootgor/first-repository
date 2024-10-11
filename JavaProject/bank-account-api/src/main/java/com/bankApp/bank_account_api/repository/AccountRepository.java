package com.bankApp.bank_account_api.repository;

import com.bankApp.bank_account_api.model.entity.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link AccountEntity}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD и специализированных запросов к
 * базе данных для получения информации о банковских счетах.
 * </p>
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    /**
     * Находит банковский счет по номеру счета и PIN-коду пользователя.
     *
     * @param accountNumber номер банковского счета.
     * @param pin           PIN-код пользователя, связанного с этим счетом.
     * @return {@link Optional} содержащий {@link AccountEntity}, если счет найден, иначе пустой
     * объект.
     */
    @Query("SELECT a FROM AccountEntity a JOIN a.user u WHERE a.id = :accountNumber AND u.pin = :pin")
    Optional<AccountEntity> findByAccountNumberAndPin(@Param("accountNumber") String accountNumber,
        @Param("pin") String pin);

}
