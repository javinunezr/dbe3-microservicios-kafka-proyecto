package com.bancoxyz.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_annual_accounts")
public class AnnualAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_id")  
  private String accountId;
  
  @Column(name = "transaction_date")
  private String date;

  @Column(name = "transaction_type")  
  private String transactionType;

  private Integer amount;

  @Column(name = "transaction_description")  
  private String transactionDescription;

}
