package com.bankApp.bank_account_api.repository;

import com.bankApp.bank_account_api.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link UserEntity}.
 * <p>
 * Этот интерфейс наследует от {@link JpaRepository} и предоставляет стандартные методы для выполнения
 * операций CRUD над сущностями пользователей.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}
