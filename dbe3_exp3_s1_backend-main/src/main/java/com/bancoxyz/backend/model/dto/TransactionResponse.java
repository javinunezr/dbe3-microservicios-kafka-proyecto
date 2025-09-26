package com.bancoxyz.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionResponse {
  private Long id;
  private String transactionDate;
  private Integer amount;
  private String type;
}
