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
@Table(name = "tbl_interests")
public class Interest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_id")  
  private String accountId;

  @Column(name = "client_name")
  private String clientName;
  
  private Integer balance;

  @Column(name = "client_age")
  private Integer clientAge;

  @Column(name = "interest_type")
  private String interestType;

  
}
