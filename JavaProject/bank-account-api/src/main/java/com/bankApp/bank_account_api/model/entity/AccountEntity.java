package com.bankApp.bank_account_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность, представляющая банковский счет. Эта сущность хранит информацию о счете, включая его
 * баланс и владельца.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class AccountEntity {

    /**
     * Уникальный идентификатор банковского счета. Хранится в виде строки.
     */
    @Id
    private String id;

    /**
     * Баланс банковского счета. Не может быть равен null.
     */
    @Column(nullable = false)
    private BigDecimal balance;

    /**
     * Владелец банковского счета. Связь многие-к-одному с сущностью UserEntity. Не может быть равен
     * null.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
