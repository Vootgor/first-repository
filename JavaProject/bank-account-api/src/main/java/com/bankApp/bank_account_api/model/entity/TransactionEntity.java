package com.bankApp.bank_account_api.model.entity;

import com.bankApp.bank_account_api.model.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность, представляющая транзакцию в банковской системе. Эта сущность хранит информацию о каждой
 * финансовой операции, включая сумму, тип транзакции, временную метку и связь с конкретным счетом.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class TransactionEntity {

    /**
     * Уникальный идентификатор транзакции. Генерируется автоматически с использованием стратегии
     * UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Сумма транзакции. Не может быть равна null.
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Тип транзакции (например, пополнение, снятие, перевод). Не может быть равен null и хранится в
     * виде строки.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    /**
     * Временная метка, указывающая на время совершения транзакции. Не может быть равна null.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * Счет, с которым связана транзакция. Связь многие-к-одному с сущностью AccountEntity. Не может
     * быть равен null.
     */
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
}
