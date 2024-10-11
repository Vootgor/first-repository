package com.bankApp.bank_account_api.utilities;

import com.bankApp.bank_account_api.dto.AccountDTO;
import com.bankApp.bank_account_api.model.entity.AccountEntity;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования между сущностью аккаунта и объектом передачи данных (DTO).
 * <p>
 * Этот интерфейс использует MapStruct для автоматического маппинга между {@link AccountEntity} и
 * {@link AccountDTO}. Позволяет упростить конвертацию данных между слоями приложения.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Преобразует сущность аккаунта в объект передачи данных (DTO).
     *
     * @param accountEntity сущность аккаунта, которую необходимо преобразовать
     * @return объект AccountDTO, представляющий данные аккаунта
     */
    AccountDTO toDto(AccountEntity accountEntity);

    AccountEntity toEntity(AccountDTO accountDTO);
}
