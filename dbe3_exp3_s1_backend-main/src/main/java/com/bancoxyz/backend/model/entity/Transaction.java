package com.bancoxyz.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_transactions")
public class Transaction {
  @Id
  private Long id;

  @Column(name = "transaction_date")  
  private String transactionDate;
  private Integer amount;
  private String type;

}