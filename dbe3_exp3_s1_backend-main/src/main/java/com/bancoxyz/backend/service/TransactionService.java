package com.bancoxyz.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoxyz.backend.model.dto.TransactionResponse;
import com.bancoxyz.backend.repository.TransactionRepository;

@Service
public class TransactionService {


  @Autowired
  private TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<TransactionResponse> findAllTransactions() {
     return transactionRepository.findAll().stream() 
      .map(transaction -> new TransactionResponse(
          transaction.getId(),
          transaction.getTransactionDate(),
          transaction.getAmount(),
          transaction.getType()
      ))
      .toList();
      
  }

}
