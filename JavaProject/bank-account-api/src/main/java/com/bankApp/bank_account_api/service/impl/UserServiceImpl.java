package com.bankApp.bank_account_api.service.impl;

import com.bankApp.bank_account_api.dto.UserDTO;
import com.bankApp.bank_account_api.model.entity.AccountEntity;
import com.bankApp.bank_account_api.model.entity.UserEntity;
import com.bankApp.bank_account_api.repository.UserRepository;
import com.bankApp.bank_account_api.service.UserService;
import com.bankApp.bank_account_api.utilities.UserMapper;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса для работы с пользователями.
 * <p>
 * Данный класс отвечает за создание и получение пользователей. Он использует репозиторий для
 * доступа к данным и маппер для преобразования сущностей в DTO.
 * </p>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * Генерирует уникальный номер аккаунта.
     *
     * @return уникальный номер аккаунта в виде строки
     */
    private String getAccountNumber() {
        return UUID.randomUUID().toString();
    }

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Конструктор для создания экземпляра UserServiceImpl.
     *
     * @param userRepository репозиторий для работы с пользователями
     * @param userMapper     маппер для преобразования пользователей в DTO
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Создает нового пользователя.
     *
     * @param userDTO данные о пользователе
     * @return созданный пользователь в виде DTO
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String accountNumber = getAccountNumber();
        UserEntity userEntity = userMapper.toEntity(userDTO);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountNumber);
        ArrayList<AccountEntity> accounts = new ArrayList<>();
        accounts.add(accountEntity);
        userEntity.setAccounts(accounts);
        UserEntity save = userRepository.save(userEntity);
        return userMapper.toDto(save);
    }

    /**
     * Получает пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return пользователь в виде DTO
     * @throws RuntimeException если пользователь не найден
     */
    @Override
    public UserDTO getUser(String id) {
        UserEntity userEntity = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));
        return userMapper.toDto(userEntity);
    }

}
