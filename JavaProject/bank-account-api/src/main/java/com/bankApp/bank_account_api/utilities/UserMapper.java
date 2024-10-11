package com.bankApp.bank_account_api.utilities;

import com.bankApp.bank_account_api.dto.UserDTO;
import com.bankApp.bank_account_api.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper для преобразования между сущностями UserEntity и UserDTO.
 * <p>
 * Этот интерфейс использует библиотеку MapStruct для автоматического
 * создания реализации маппера, что упрощает преобразование между
 * сущностями и DTO (Data Transfer Object).
 * </p>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует сущность UserEntity в UserDTO.
     *
     * @param userEntity сущность, которую нужно преобразовать
     * @return соответствующий объект UserDTO
     */
    @Mapping(target = "accountDTO", source = "accounts")
    UserDTO toDto(UserEntity userEntity);

    /**
     * Преобразует объект UserDTO в UserEntity.
     *
     * @param userDTO объект, который нужно преобразовать
     * @return соответствующая сущность UserEntity
     */
    @Mapping(target = "accounts", source = "accountDTO")
    UserEntity toEntity(UserDTO userDTO);
}
