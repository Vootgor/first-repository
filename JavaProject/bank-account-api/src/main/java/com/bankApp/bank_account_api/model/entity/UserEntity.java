package com.bankApp.bank_account_api.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность, представляющая пользователя в банковской системе. Эта сущность хранит информацию о
 * пользователе, включая его имя, PIN-код и связанные банковские счета.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class UserEntity {

    /**
     * Уникальный идентификатор пользователя. Генерируется автоматически с использованием стратегии
     * UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Имя пользователя. Не может быть равно null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * PIN-код пользователя. Не может быть равно null.
     */
    @Column(nullable = false)
    private String pin;

    /**
     * Список связанных банковских счетов. Связь один-ко-многим с сущностью AccountEntity. При
     * удалении пользователя, связанные счета также будут удалены.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts;
}
