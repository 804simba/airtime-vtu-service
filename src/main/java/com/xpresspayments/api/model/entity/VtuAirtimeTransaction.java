package com.xpresspayments.api.model.entity;

import com.xpresspayments.api.model.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "airtime_txn_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VtuAirtimeTransaction extends BaseEntity {

    @ManyToOne
    private User user;

    private BigDecimal amount;

    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
