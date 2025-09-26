package com.bancoxyz.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class AnnualAccountResponse {
  private Long id;
  private String accountId;
  private String date;
  private String transactionType;
  private Integer amount;
  private String transactionDescription;
}
