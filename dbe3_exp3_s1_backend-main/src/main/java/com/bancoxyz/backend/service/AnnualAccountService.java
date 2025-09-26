package com.bancoxyz.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoxyz.backend.model.dto.AnnualAccountResponse;
import com.bancoxyz.backend.repository.AnnualAccountRepository;

@Service
public class AnnualAccountService {
  
  @Autowired
  private AnnualAccountRepository annualAccountRepository;

  public AnnualAccountService(AnnualAccountRepository annualAccountRepository) {
    this.annualAccountRepository = annualAccountRepository;
  }

  public List<AnnualAccountResponse> findAllAnnualAccounts() {
     return annualAccountRepository.findAll().stream() 
      .map(annualAccount -> new AnnualAccountResponse(
          annualAccount.getId(),
          annualAccount.getAccountId(),
          annualAccount.getDate(),
          annualAccount.getTransactionType(),
          annualAccount.getAmount(),
          annualAccount.getTransactionDescription()
      ))
      .toList();
      
  }

}
