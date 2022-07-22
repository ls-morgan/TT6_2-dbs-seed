package com.dbs.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "wallet_id")
    private int walletId;
    @Column(name = "debit_id")
    private int debitId;
    @Column(name = "debit_currency")
    private String debitCurrency;
    @Column(name = "debit_amount")
    private BigDecimal debitAmount;
    @Column(name = "credit_id")
    private int creditId;
    @Column(name = "credit_amount")
    private BigDecimal creditAmount;
    @Column(name = "description")
    private String Description;


}
