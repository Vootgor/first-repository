package com.bankApp.bank_account_api.utilities;

import com.bankApp.bank_account_api.dto.TransactionDTO;
import com.bankApp.bank_account_api.model.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper для преобразования между сущностями TransactionEntity и TransactionDTO.
 * <p>
 * Этот интерфейс использует библиотеку MapStruct для автоматического
 * создания реализации маппера, что упрощает преобразование между
 * сущностями и DTO (Data Transfer Object).
 * </p>
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    /**
     * Преобразует сущность TransactionEntity в TransactionDTO.
     *
     * @param transactionEntity сущность, которую нужно преобразовать
     * @return соответствующий объект TransactionDTO
     */
    @Mapping(target = "accountNumber", source = "account.id")
    TransactionDTO toDto(TransactionEntity transactionEntity);

}
